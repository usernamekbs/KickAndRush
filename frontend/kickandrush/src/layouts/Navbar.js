import React,{ useState,useEffect } from 'react'; 
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { Link } from 'react-router-dom';
import axios from 'axios'
import { useNavigate } from 'react-router-dom';
import { Height } from '@mui/icons-material';

const Navbar = () => {
    const userId = localStorage.getItem('userId');
    const email = localStorage.getItem('email');
    const role = localStorage.getItem('role');
    const accessToken = localStorage.getItem('accessToken');
    const [categoryList,setCategoryList] = useState();
    const navigate = useNavigate();

    const fetchCategory = async () =>{
        try {  
            const result = await axios.get(`http://localhost:8080/api/category/list`)
            setCategoryList(result.data)
          } catch (error) {
            console.log(error);
          }
    }

    const handleLogout = () => {
        localStorage.clear();
        navigate('/logout');
    };

    useEffect(() => { 
        const expirationTime = Date.now() + 3600 * 1000; 
        const currentTime = Date.now();
        console.log(currentTime) 
        console.log(expirationTime + 6 * 60 * 1000) 
        if (currentTime > expirationTime + 6 * 60 * 1000) { 
            localStorage.clear();
            alert('로그아웃되었습니다')
            
            navigate('/login'); 
        } else {
            fetchCategory();
        }
    }, [navigate]);
    
    return (
        <Box>
         
        <AppBar position="static" style={{backgroundColor:'#79edff'}}>
            <Toolbar style={{display:'flex'}}>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2,color:'black' }}
            >
                <MenuIcon />
            </IconButton>
            {categoryList && categoryList.map((category) => (
                <Typography key={category.id} variant="h8" component="div" sx={{ flexGrow: 'auto' }}>
                    <Link to={{
                            pathname: `/category/${category.id}/${category.name}`,
                        }}
                        style={{ textDecoration: "none", color:'black',marginLeft:'20px'}}>
                        {category.name}
                         
                    </Link> 
                    
                </Typography>
                
            ))} 
            {role === 'ADMIN' && (
                <Link to={{
                        pathname: `/user`,
                    }}
                    style={{ textDecoration: "none", color:'black',marginLeft:'20px'}}>
                    user
                </Link>
            )} 

            {accessToken ?  
                 <Link to={{
                       pathname: `/user/view/${userId}`,
                    }} 
                    style={{ textDecoration: "none", color:'black',margin:'0 0 0 auto'}}
                >{email}정보</Link> 
                : 
                null
            }

            {accessToken ?
                <Button style={{color:'black',margin:'0 - 0 auto'}} color="inherit" onClick={handleLogout}>로그아웃</Button>
                :   
                <Button style={{color:'black',marginLeft:'auto'}} color="inherit" href={`/login`}>Login</Button>
                
            } 
            </Toolbar>
        </AppBar>
        </Box>
    );

}
export default Navbar;