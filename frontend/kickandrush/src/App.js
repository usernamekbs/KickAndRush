import React from 'react'
import { BrowserRouter as Router , Routes, Route } from "react-router-dom"
// import AddPost from './components/post/AddPost'
// import Post from './components/post/Post'
// import DetailPost from './components/post/DetailPost'
// import UpdatePost from './components/post/UpdatePost'
// import LoginForm from './components/user/LoginForm'
// import AddMember from './components/user/AddMember'
// import NotFound from './components/error/NotFound'
// import Category from './components/category/Category'

import Navbar from './components/layout/Navbar'

import 'antd/dist/antd.css'

const App = () =>{
  return (
    <> 
      <Router>
        <Navbar>
        <Routes> 
              {/* <Route path={"/login"} element={ <LoginForm/> } />
              // <Route path={"/addMember"} element={ <AddMember/> } /> */}
              {/* <Route path={"/"} element={ <Post/> } /> */}
              {/* <Route path={"/list"} element={ <Post/>} />
              <Route path={"/update"} element={ <UpdatePost/>} />
              <Route path={"/addPost"} element={ <AddPost />} />
              <Route path={"/view/:pno"} element={ <DetailPost />} /> 
              <Route path={"/category"} element={ <Category />} /> 
              <Route path={"*"} element={ <NotFound /> } />  */}
            </Routes>
        </Navbar>
      </Router>
   </>
  );
}

export default App;