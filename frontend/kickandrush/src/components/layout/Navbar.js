import React, { useState,useEffect } from "react";
import 'antd/dist/antd.css'
import { Breadcrumb,Layout,  Menu, theme } from 'antd';
import { BrowserRouter as Router , Routes, Route,Link } from "react-router-dom"
import CategoryList from "../category/CategoryList";
import Post from "../post/Post";
import UpdatePost from "../post/UpdatePost";
import AddPost from "../post/AddPost";
import DetailPost from "../post/DetailPost";
import AddMember from "../user/AddMember";
import LoginForm from "../user/LoginForm";
import NotFound from '../error/NotFound'
import service from "../../services/categoryService";

const { Header, Content, Footer } = Layout;

const Navbar = () => {
    const [category, setCategory] = useState([]);

    useEffect(() => {  
        listCategory()
    }, []);  
    
    const listCategory = () => {
        service.getAll().then(response => {
            setCategory(response.data)
        })
        .catch(e => {  
            console.log(e);
        });  
    };

    const delCategory = (ctg_cno) =>{
        setCategory(category.filter(c => c.ctg_cno !== ctg_cno))
    }

    const addCategory2 = (newCategory) =>{
        setCategory(category.concat(newCategory))
    }

    const updCategory = (newCategory) =>{
    
        setCategory(category.filter(c =>{
            if(c.ctg_cno === newCategory.ctg_cno){
                c.title = newCategory.title 
            }
            return c
        })
        )
    }

    const items = category.map((c) => (
        {
            label: <Link to={c.url}>{c.title}</Link>,
        }
    ));

    return (
        <>
            <Layout  className="layout">
                <Header>
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        items={items}
                    />
                </Header>
                <Content
                    style={{
                        padding: 24,
                        margin: 0,
                        minHeight: 280
                    }}>
                        <Routes>
                            <Route path={"/"} element={ <Post /> } />
                            <Route path={"/login"} element={ <LoginForm /> } />
                            <Route path={"/addMember"} element={ <AddMember/> } />
                            <Route path={"/list"} element={ <Post/>} />
                            <Route path={"/update"} element={ <UpdatePost/>} />
                            <Route path={"/addPost"} element={ <AddPost />} />
                            <Route path={"/view/:pno"} element={ <DetailPost />} />
                            <Route path={"/category"} element={ <CategoryList props={category} delCategory={delCategory} addCategory2={addCategory2} updCategory={updCategory} />} /> 
                            <Route path={"*"} element={ <NotFound /> } /> 
                            <Route path={"/home"} element={ <Post />}/>
                        </Routes>
                </Content>
                <Footer/>
            </Layout>
        </> 
    );
  };
  
  export default Navbar;