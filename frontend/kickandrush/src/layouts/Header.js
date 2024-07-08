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
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import '../css/Layout.css'

const Header = () => {
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
        if (currentTime > expirationTime + 6 * 60 * 1000) { 
            localStorage.clear();
            alert('로그아웃되었습니다')
            
            navigate('/login'); 
        } else {
            fetchCategory();
        }
    }, [navigate]);
    
    return (
        <>
            <header>
                <section className="sub">
                    <ul>
                    {accessToken ?
                        <Link onClick={handleLogout}><li>로그아웃</li></Link>
                        :   
                        <div>
                            <Link to={'/login'}><li>회원가입</li></Link>
                            <Link to={'/login'}><li>Login</li></Link>
                        </div>
                    } 
                    </ul>
                </section>
                <section className="main">
                    <div>
                        <h3>KICKANDRUSH</h3>
                    </div>
                </section>

                {role==='ADMIN' && 
                    <section className="category">
                        <nav> 
                            <ul>
                                {categoryList && categoryList.map((category) => (
                                    <li key={category.id}>
                                        <Link to={{
                                                pathname: `/category/${category.id}/${category.name}`,
                                            }} style={{color:'white'}}
                                        >
                                            {category.name}
                                        </Link> 
                                    </li>
                                    
                                ))} 
                            </ul> 
                        </nav>
                    </section>
                }

                {role!=='ADMIN'&&
                    <section className="category">
                        <nav> 
                            <ul>
                                {categoryList && categoryList
                                .filter((category) => category.name !== 'user')
                                .map((category) => (

                                    <li key={category.id}>
                                        <Link to={{
                                                pathname: `/category/${category.id}/${category.name}`,
                                            }} style={{color:'white'}}
                                        >
                                            {category.name}
                                        </Link> 
                                    </li>
                                ))} 
                            </ul> 
                        </nav>
                    </section>
                }
                
            </header>
        </>
        // <Box>
         
        // <AppBar position="static" style={{backgroundColor:'#79edff'}}>
        //     <Toolbar style={{display:'flex'}}>
        //     <IconButton
        //         size="large"
        //         edge="start"
        //         color="inherit"
        //         aria-label="menu"
        //         sx={{ mr: 2,color:'black' }}
        //     >
        //         <MenuIcon />
        //     </IconButton>
            // {categoryList && categoryList.map((category) => (
            //     <Typography key={category.id} variant="h8" component="div" sx={{ flexGrow: 'auto' }}>
            //         <Link to={{
            //                 pathname: `/category/${category.id}/${category.name}`,
            //             }}
            //             style={{ textDecoration: "none", color:'black',marginLeft:'20px'}}>
            //             {category.name}
                         
            //         </Link> 
                    
            //     </Typography>
                
            // ))} 
            // {role === 'ADMIN' && (
        //         <Link to={{
        //                 pathname: `/user`,
        //             }}
        //             style={{ textDecoration: "none", color:'black',marginLeft:'20px'}}>
        //             user
        //         </Link>
        //     )} 

        //     {accessToken ?  
        //          <Link to={{
        //                pathname: `/user/view/${userId}`,
        //             }} 
        //             style={{ textDecoration: "none", color:'black',margin:'0 0 0 auto'}}
        //         >{email}정보</Link> 
        //         : 
        //         null
        //     }

            // {accessToken ?
            //     <Button style={{color:'black',margin:'0 - 0 auto'}} color="inherit" onClick={handleLogout}>로그아웃</Button>
            //     :   
            //     <Button style={{color:'black',marginLeft:'auto'}} color="inherit" href={`/login`}>Login</Button>
                
            // } 
        //     </Toolbar>
        // </AppBar>
        // </Box>
    );

}
export default Header;