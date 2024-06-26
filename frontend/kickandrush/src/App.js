import { BrowserRouter,Routes,Route } from 'react-router-dom';
import User from "./components/user/User";
import CreateUser from "./components/user/CreateUser";
import ViewUser from "./components/user/ViewUser";
import UpdateUser from "./components/user/UpdateUser";
import Login from "./components/user/Login";
import Post from "./components/post/Post";
import CreatePost from "./components/post/CreatePost";
import ViewPost from "./components/post/ViewPost";
import UpdatePost from "./components/post/UpdatePost";
import Gallery from "./components/gallery/Gallary"
import ViewGallery from "./components/gallery/ViewGallery"
import CreateGallery from "./components/gallery/CreateGallery"
import UpdateGallery from "./components/gallery/UpdateGallery"
import NaverRedirectPage from "./components/user/NaverRedirectPage"
import MainLayout from './layouts/MainLayout'
import Formation from "./components/formation/Formation"

const App = () => {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainLayout />}>
              <Route path="/category/:categoryId/user" element={<User />}/> 
              <Route path="/user/create" element={<CreateUser />}/> 
              <Route path="/user/view/:id" element={<ViewUser />}/> 
              <Route path="/user/update/:id" element={<UpdateUser />}/> 
              <Route path="/login" element={<Login />}/> 
              <Route path="/category/:categoryId/post" element={<Post />}/> 
              <Route path="/category/:categoryId/post/create" element={<CreatePost />}/> 
              <Route path="/category/:categoryId/post/view/:id" element={<ViewPost />}/> 
              <Route path="/category/:categoryId/post/update/:id" element={<UpdatePost />}/> 
              <Route path="/category/:categoryId/gallerys" element={<Gallery />}/> 
              <Route path="/category/:categoryId/gallery/view/:id" element={<ViewGallery />}/> 
              <Route path="/category/:categoryId/gallery/create" element={<CreateGallery />}/> 
              <Route path="/category/:categoryId/gallery/update/:id" element={<UpdateGallery />}/> 
              <Route path="/callback" element={<NaverRedirectPage />}></Route>
              <Route path="/category/:categoryId/formation" element={<Formation/>}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
  );
} 

export default App;
