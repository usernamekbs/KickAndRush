import React, { useState, useEffect } from 'react';
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";

const Login = () => {

    const handleLogin = () => {
        window.location.href = 'http://localhost:8080/api/user/login'; // 서버의 /login 엔드포인트로 리다이렉트
      };

    return (
        <Container component="main" maxWidth="xs">
            <Box
                sx={{  
                marginTop: 8,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                }}
            >
            <Box component="form" noValidate sx={{ mt: 1 }}>
       
            <div style={{dipslay:'plex', borderRadius:'7.5px', backgroundColor:'#03C75A'}}>
            <Button
                onClick={handleLogin} 
            >
                <span style={{color:'white'}}>네이버 로그인</span>
            </Button>
            </div>
            </Box>
            </Box>
        </Container>
      );

}
export default Login;