import React, { useState, useEffect } from 'react';
import axios from "axios";
import { Link,useParams } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Button from "@mui/material/Button";
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { Container, TextField } from "@mui/material";
import "../../css/Formation.css"

const Post = () => {
    const [postList,setPostList] = useState([]);
    const [postTotal,setPostTotal] = useState(0); //전체 개시글수
    const [postTotalPages,setPostTotalPages] = useState(0) //전체 페이징 수
    const [postPage,setPostPage] = useState(1)
    const [searchType, setSearchType] = useState("Title");
    const [searchText, setSearchText] = useState("");
    const category = useParams();
    const accessToken = localStorage.getItem('accessToken')

    const handleChange = (e,value) => {
      setPostPage(value); 
    };

    const fetchPost = async () => {
      
      try {  
        const result = await axios.get(`http://localhost:8080/api/post/list?page=${postPage}`, {
          params: {
            searchText: searchText,
            searchType: searchType 
          }
        });
        setPostList(result.data.content) 
        setPostTotal(result.data.totalElements) 
        setPostTotalPages(result.data.totalPages)
      } catch (error) {
        console.log(error)
      }
    };

    useEffect(() => {
        fetchPost();
    }, [postPage]); 

    return (
      <div>
        <div style={{display:'flex',justifyContent:'center'}}>
          <h2>게시글 개수 :{postTotal}</h2>
        </div>
        
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 1200}} aria-label="simple table">
            <TableHead style={{backgroundColor:'aqua'}}>
              <TableRow>
                <TableCell>번호</TableCell>
                <TableCell align="right">카테고리</TableCell>
                <TableCell align="right">제목</TableCell>
                <TableCell align="right">이메일</TableCell>
                <TableCell align="right">조회수</TableCell>
                <TableCell align="right">이미지 개수</TableCell>
                <TableCell align="right">작성일</TableCell>
                <TableCell align="right">수정일</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {postList.length === 0 ? (
                    <TableRow>
                        <TableCell colSpan={8} align="center" style={{color: 'red'}}>데이터가 없습니다.</TableCell>
                    </TableRow>
                ) :
                postList.map((post) => (
                <TableRow key={post.id}>
                  <TableCell component="th" scope="row">
                      <Link to={`/category/${category.categoryId}/post/view/${post.id}`}>
                          {post.id}
                      </Link>
                  </TableCell>
                  <TableCell align="center"><div style={{borderRadius:'6px',backgroundColor:'#eee', textDecorationLine: 'underline'}}>{post.name}</div></TableCell>
                  <TableCell align="right">{post.title} <span style={{color:'red'}}>[{post.commentCnt}]</span></TableCell>
                  <TableCell align="right">{post.email}</TableCell>
                  <TableCell align="right">{post.views}</TableCell> 
                  <TableCell align="right">{post.imageCnt}</TableCell>
                  <TableCell align="right">{post.createdDate}</TableCell>
                  <TableCell align="right">{post.modifiedDate}</TableCell>

                </TableRow>
              ))
            }
            </TableBody>
          </Table>
          {accessToken && (
            <Link to={`/category/${category.categoryId}/post/create`}>
                <Button variant="contained" color="primary">저장</Button>
            </Link>
          )} 
          <div style={{display:'flex',justifyContent:'center'}}>
            <Stack spacing={2}>
              <Pagination count={postTotalPages} page={postPage} onChange={handleChange} variant="outlined" color="primary" />
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
                <MenuItem value="Title"><em>제목</em></MenuItem>
                <MenuItem value="Content">내용</MenuItem>
                <MenuItem value="Username">유저이름</MenuItem>
              </Select>
              <TextField type="search" id="searchText" label="Search" sx={{ width: 600 }} onChange={(e) => setSearchText(e.target.value)}/>
              <Button variant="contained" onClick={fetchPost}>검색</Button>
            </div>
        </TableContainer> 
      </div>
      );

}
export default Post;