import React, { useState, useEffect } from 'react';
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import AddToPhotosIcon from '@mui/icons-material/AddToPhotos';
import CardMedia from '@mui/material/CardMedia';

const CreateGallery = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const categoryId = useParams().categoryId;
    const [readerImages,setReaderImages] = useState();
    const [thumb,setThumb] =useState();
    const [msg,setErrorMsg] = useState('');
    const navigate = useNavigate();
    const accessToken = localStorage.getItem('accessToken')
    const userId = localStorage.getItem('userId')

    const handleChange = e => {
        const images = e.target.files[0];
        setThumb(images)
        const reader = new FileReader();
        if(images){
            reader.readAsDataURL(images);
        }
        reader.onload = () => {
            setReaderImages(reader.result);
        };
    };

    const createGallery = async(e) => {
        e.preventDefault()
        const formData = new FormData();

        const data = {
            title       : title,
            content     : content,
            userId      : userId,
            categoryId  : categoryId
        }
        formData.append('file', thumb); 
        formData.append("requestGalleryDto", new Blob([JSON.stringify(data)], {
            type: "application/json"
        }));

        try {
            await axios.post(`http://localhost:8080/api/gallery/create/`,formData);
            navigate('/category/4/gallerys')
        
        } catch (error) { 
            if (error.response && error.response.status === 400) {
                setErrorMsg(error.response.data)
            }
        }
    };
   
    useEffect(() => {
        if(!accessToken){
            navigate('/login')
        }
    }, [categoryId]); 

   

    return (
        <Card sx={{ width:'1200px' }}>
            <Box>
                <Typography variant="h5">게시글 쓰기</Typography>
            </Box>
           
            <CardMedia
                sx={{ height: 300,width:300}}
                image={readerImages} 
                title="green iguana"
            />
            <Box height={"100vh"}>
                <TextField
                fullWidth
                label="제목"
                type="text"
                variant="standard"
                onChange={(e) => setTitle(e.target.value)}
                />
                {msg.title&&
                    <div style={{display:'flex',justifyContent:'center',color:'red'}}>
                        {msg.title}
                    </div>
                }
                <TextField
                fullWidth
                label="내용"
                type="text"
                variant="standard"
                onChange={(e) => setContent(e.target.value)}
                />
                <Button style ={{marginTop:'20px',width:'100%'}}variant="contained" component="label" color="primary">
                    {" "}
                    <AddToPhotosIcon /> Upload a file
                        <input type="file" 
                            onClick={(e)=>handleChange(e)}
                            onChange={handleChange} hidden/>
                </Button>
            </Box>
            
            <Button fullWidth onClick={createGallery} variant="contained">
                저장
            </Button>
            
        </Card>
      );

}
export default CreateGallery;