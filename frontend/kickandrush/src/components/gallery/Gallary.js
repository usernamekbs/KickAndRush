import React, { useState, useEffect } from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import { CardMedia } from '@mui/material';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { TextField } from "@mui/material";
import { Link,useParams } from 'react-router-dom';
import axios from "axios";
import '../../css/Gallery.css'

const Gallery = () => {
    const [galleryList,setGalleryList] = useState([]);
    const [galleryTotal,setGalleryTotal] = useState(0); 
    const [galleryTotalPages,setGalleryTotalPages] = useState(0)
    const [galleryPage,setGalleryPage] = useState(1)
    const [searchType, setSearchType] = useState("Title");
    const [searchText, setSearchText] = useState("");
    const accessToken = localStorage.getItem('accessToken')
    const email = localStorage.getItem('email')
    const category = useParams();

    const handleChange = (e,value) => {
        setGalleryPage(value); 
      };

    const fetchGallery = async () => {
      
        try {  
            const result = await axios.get(`http://localhost:8080/api/gallery/list?page=${galleryPage}`,
            {params: {  
                searchText : searchText,
                searchType : searchType
              },
            })    
            setGalleryList(result.data.content) 
            setGalleryTotal(result.data.totalElements) 
            setGalleryTotalPages(result.data.totalPages)
        } catch (error) {
          console.log(error); 
        }
      };

    useEffect(() => {
        fetchGallery()   

    }, [galleryPage]); 

    return (
            <>
            <div style={{display:'flex',justifyContent:'center'}}>
                <h2>게시글 개수 :{galleryTotal}</h2>
            </div>
            <div className='gallery'>
                {galleryList.length === 0 ? (
                    <div style={{display:'flex',justifyContent:'center',color:'red'}}>데이터가 없습니다</div>
                ) : galleryList.map((gallery)=>{
                    return (
                    <div>
                        <Card sx={{ minWidth: 250,maxWidth:500,minHeight:350,maxHeight:250 }}>
                            <CardHeader
                            title={"작성자:"+gallery.email}
                            subheader={<div style={{display:'flex',justifyContent:'center',borderRadius:'6px',backgroundColor:'#eee', textDecorationLine: 'underline'}}><span style={{backgroundColor:'#eee'}}>{gallery.name}</span></div>}
                            />
                            <Link style={{textDecoration: 'none'}} to={`/category/${category.categoryId}/gallery/view/${gallery.id}`}>
                            <CardMedia
                                sx={{ height: '200px' }}
                                image={gallery.filePath} 
                                title="green iguana"
                            />
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                {gallery.title}
                                </Typography>
                            </CardContent>
                            </Link>
                        </Card>
                        
                    </div>
                    )
                })}
            </div>
            {/* 유저가 로그인했는지 여부 체크 */}
            {accessToken &&(
                <Link to={`/category/${category.categoryId}/gallery/create`} style={{ textDecoration: "none", color:'white' }}>
                    <Button variant="contained" color="primary">저장</Button>
                </Link>
            
            )}
            <Stack spacing={2}>
                <Pagination style={{display:"flex",justifyContent:"center",marginTop:'10px'}} count={galleryTotalPages} page={galleryPage} onChange={handleChange} variant="outlined" color="primary" />
            </Stack>
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
                <TextField  type="search" id="searchText" label="Search" sx={{ width: 600 }} onChange={(e) => setSearchText(e.target.value)}/>
                <Button style={{height:"56px"}} variant="contained" onClick={fetchGallery}>검색</Button>
            </div>
        </>
      );

}
export default Gallery;