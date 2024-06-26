import React, { useState, useEffect } from 'react';
import Card from "@mui/material/Card";
import Button from "@mui/material/Button";
import { Typography } from "@mui/material";
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import axios from "axios";
import { useParams,useNavigate,Link  } from 'react-router-dom';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import Comment from "../comment/Comment"
import CreateComment from '../comment/CreateComment'

const ViewPost = () => {
    const id = useParams().id;
    const [post, setPost] = useState({});
    const [likeCount, setLikeCount] = useState(0);
    const navigate = useNavigate();
    const categoryId = useParams().categoryId;
    const userId = localStorage.getItem('userId');
    const accessToken = localStorage.getItem('accessToken');
    const email = localStorage.getItem('email');

    const onePost = async () => {
      try {
        const result = await axios.get(`http://localhost:8080/api/post/view/${id}`
        ,     
        );
        console.log(result) 
        setLikeCount(result.data.likeCnt)
        setPost(result.data)     
        console.log(result.data)    
      } catch (error) { 
        console.log(error);
      }
    };

    useEffect(() => {
          onePost();
    }, []);   

    const deletePost = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/post/delete/${id}`);
            navigate(`/category/${categoryId}/post`)
        } catch (error) { 
          console.log(error);
        }
    };

    const likePost = async () => {
      try {
          const data = {
            userId:userId,
            postId:id
          } 
          const result = await axios.post(`http://localhost:8080/api/likes/create`,data);
          setLikeCount(result.data)
      } catch (error) { 
        console.log(error);
      }
    };

    //댓글 추가 
    const updateComment = (...comment) => {
      setPost((prevComment)=>{
        return {
          ...prevComment,
          comments : post.comments.concat(comment), 
        }
      })
    }    

    return (
        <Card sx={{ width:'950px',margin:'0'}}>
          <CardContent>
            <Typography variant="h5" component="div"> 
                카테고리 : {post.category}
                번호 : {post.id} 
                <Button style={{fontSize:'20px',marginLeft:"50%",color:'#99ccff'}} onClick={likePost}>
                <ThumbUpAltIcon style={{color:'#99ccff'}}></ThumbUpAltIcon>좋아요{likeCount}</Button> 
            </Typography>
            <Typography variant="body1">
                제목 : {post.title}
            </Typography>
            <Typography variant="body1">
                내용 : {post.content}
            </Typography>
            <Typography variant="body1">
                작성자 : {post.email} 
            </Typography>
            <Typography variant="body1">
                조회수 : {post.views}
            </Typography>
            {post.images && post.images.map((i)=>{
              return (
                <div key={i.id}>
                  <img src={require(`../../upload/${i.origName}`)} style={{width:'100%'}}/>
                </div>
              )
              })
            } 
          
        </CardContent>
        {accessToken && email === post.email &&
          <CardActions>
                <Link to={`/category/${categoryId}/post/update/${id}`} state={{ post: post}}> 
                  <Button variant="contained" color="success">수정</Button>
                </Link>
                <Button variant="contained" color="error" onClick={deletePost}>삭제</Button>
              
          </CardActions>
        } 
        {post.comments && post.comments.map((c)=>{  
          
          return ( 
            <Comment comments={c} key={c.id} setPost={setPost} />
            )
          })
        }
        <CreateComment postId={id} refreshFunction={updateComment} />
        </Card>
      );
}

export default ViewPost;