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
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const categoryId = useParams().categoryId;
    const [readerImages, setReaderImages] = useState([]);
    const [msg,setErrorMsg] = useState('');
    const navigate = useNavigate();
    const accessToken = localStorage.getItem('accessToken')
    const userId = localStorage.getItem('userId')

    const handleChange = e => {
        const images = e.target.files;
        for (let i = 0; i < images.length; i++) {
            const reader = new FileReader();

            reader.onload = (e) => {
                setReaderImages((prevImages) => [
                    ...prevImages,
                    {
                        file: images[i],
                        previewUrl: e.target.result,
                    },
                ]);
            };
            reader.readAsDataURL(images[i]);
        }
    };

    const createPost = async (e) => {
        e.preventDefault();
        const formData = new FormData();

        const data = {
            title: title,
            content: content,
            categoryId: categoryId,
            userId: userId
        };

        readerImages.forEach((i) => {
            formData.append("files", i.file);
        });

        formData.append("requestPostDto", new Blob([JSON.stringify(data)], {
            type: "application/json"
        }));

        try {
            await axios.post(`http://localhost:8080/api/post/create/`, formData);
            navigate(`/category/${categoryId}/post`);
        } catch (error) {
            //validation은 400에러
            if (error.response && error.response.status === 400) {
                setErrorMsg(error.response.data)
            }
        }
    };

    const deletePreviewImage = (index) => {
        const files = readerImages.filter((f, idx) => idx !== index);
        setReaderImages(files);
    };

    const handleClick = (e) => {
        e.target.value = '';
    };

    useEffect(() => {
        if(!accessToken){
            navigate('/login')
        }        
    }, [readerImages]);

    return (
        <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <Card style={{width:'1200px'}}>
                <Box>
                    <Typography variant="h5">게시글 쓰기</Typography>
                </Box>
                
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
                    <Button 
                        style={{ marginTop: '20px', width: '100%' }} 
                        variant="contained" 
                        component="label" 
                        color="primary"
                    >
                    <AddToPhotosIcon /> Upload a file
                    <input 
                        type="file" 
                        multiple 
                        onClick={handleClick} 
                        onChange={handleChange} 
                        hidden
                    />
                    </Button>
                    <ImageList sx={{ width: 500, height: 450 }} cols={2} rowHeight={164}>
                        {readerImages.map((image, index) => (
                            <ImageListItem key={index}>
                                <img 
                                    srcSet={image.previewUrl}
                                    src={image.previewUrl}
                                    alt={image.file.name}
                                    loading="lazy"
                                />
                                <ImageListItemBar
                                    title={<Button 
                                        style={{ backgroundColor: "#d32f2f", color: "white" }} 
                                        onClick={() => deletePreviewImage(index)}
                                    >
                                        {image.file.name} X
                                    </Button>}
                                    position="below"
                                />
                            </ImageListItem>
                        ))}
                    </ImageList>
                </Box>
                <Button fullWidth onClick={createPost} variant="contained">
                    저장
                </Button>
            </Card>
        </div>
    );
}

export default CreatePost;