import React, { useState, useEffect } from 'react';
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

const Formation = () => {
    const accessToken = localStorage.getItem('accessToken')
    const category = useParams();
     
    useEffect(() => {

    }, []); 

    return (
        <>
            <h2>해당 게시판은 포메이션 대결 메뉴입니다 </h2>

            <div style={{ display: 'flex', justifyContent: 'center',gap:'50px' }}>
                <div style={{ 
                    width: '195px', 
                    height: '272px', 
                    textAlign: 'center', 
                    backgroundImage: `url(${process.env.PUBLIC_URL}/images/backgroundImage.jpg)`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                    }}>
                        </div>
                <div style={{ 
                    width: '195px', 
                    height: '272px', 
                    textAlign: 'center', 
                    backgroundImage: `url(${process.env.PUBLIC_URL}/images/backgroundImage.jpg)`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                    }}>
                </div>
                
            </div>
            {accessToken &&(
                <Link to={`/category/${category.categoryId}/formation/create`} style={{ textDecoration: "none", color:'white' }}>
                    <Button variant="contained" color="primary">저장</Button>
                </Link>
            
            )}
        </>
      );

}
export default Formation;