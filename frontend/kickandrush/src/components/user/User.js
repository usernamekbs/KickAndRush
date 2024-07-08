import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from "axios";
import { Link } from 'react-router-dom';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Button from "@mui/material/Button";
import Pagination from '@mui/material/Pagination';
import { Container, TextField } from "@mui/material";
import Stack from '@mui/material/Stack';

const User = () => {
    const [userList,setUserList] = useState([]);
    const [userPage,setUserPage] = useState(1)
    const [searchType, setSearchType] = useState("NAME");
    const [searchText, setSearchText] = useState("");
    const [userTotalPages,setUserTotalPages] = useState('1')

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
        [`&.${tableCellClasses.head}`]: {
          backgroundColor: theme.palette.common.black,
          color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
          fontSize: 14,
        },
      }));
      
      const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&:nth-of-type(odd)': {
          backgroundColor: theme.palette.action.hover,
        },
        '&:last-child td, &:last-child th': {
          border: 0,
        },
      }));
      

    const handleChange = (e,value) => {
      setUserPage(value); 
    };
    const fetchUser = async () => {
      try { 
        const result = await axios.get(`http://localhost:8080/api/user/list/?page=${userPage}`, {
          params: {
            searchText: searchText,
            searchType: searchType 
          }
        });
        console.log(result)  
        setUserList(result.data.content)   
      } catch (error) {
        console.log(error);
      }
    };
    useEffect(() => {
        
          fetchUser();
    }, []);  

    return (
      <div>
        <TableContainer component={Paper}>
        <Table sx={{ minWidth: 1200 }} aria-label="customized table">
            <TableHead>
            <TableRow>
                <StyledTableCell>유저 번호</StyledTableCell>
                <StyledTableCell align="right">유저 이름</StyledTableCell>
                <StyledTableCell align="right">유저 이메일</StyledTableCell>
            </TableRow>
            </TableHead>
            <TableBody>
            {userList.length ===0 ?
            <TableRow>
              <TableCell colSpan={8} align="center" style={{color: 'red'}}>데이터가 없습니다.</TableCell>
            </TableRow> 
            :
            userList && userList.map((user) => (
                <StyledTableRow key={user.naverId}>
                  <StyledTableCell component="th" scope="row">
                      <Link to={`/user/view/${user.naverId}`}>{user.naverId}</Link>
                  </StyledTableCell>
                  <StyledTableCell align="right">{user.name}</StyledTableCell>
                  <StyledTableCell align="right">{user.email}</StyledTableCell>
                </StyledTableRow>
            ))}
            </TableBody>
        </Table>
        </TableContainer>
        <div style={{display:'flex',justifyContent:'center'}}>
            <Stack spacing={2}>
              <Pagination count={userTotalPages} page={userPage} onChange={handleChange} variant="outlined" color="primary" />
            </Stack>
          </div>
          <div style={{display:'flex',justifyContent:'center'}}>
              <Select
                labelId="demo-simple-select-autowidth-label"
                id="demo-simple-select-autowidth"
                onChange={(e)=> setSearchType(e.target.value)}
                autoWidth
                label="search"
                defaultValue="Title"   
                value={searchType}
              >
                <MenuItem value="NAME"><em>이름</em></MenuItem>
                <MenuItem value="EMAIL">이메일</MenuItem>
              </Select>
              <TextField type="search" id="searchText" label="Search" sx={{ width: 600 }} onChange={(e) => setSearchText(e.target.value)}/>
              <Button variant="contained" onClick={fetchUser}>검색</Button>
            </div>
      </div>
      );

}
export default User;