import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from '@mui/material/TextField';
import Box from "@mui/material/Box";
import axios from 'axios'

const CreateComment = (props) => {
    const [comment, setComment] = useState('');   
    const userId = localStorage.getItem('userId')
    const [msg,setErrorMsg] = useState('');
    const accessToken = localStorage.getItem('accessToken')
    
    const handleChange = (e) => {
        setComment(e.currentTarget.value)  
    }

    const handleAddComment = async(e) => {
        e.preventDefault();
        
        const data = {
           content  : comment,
           userId : userId,
           postId   : props.postId, 
           parent   : null
        }
         
        try {
            const result= await axios.post(`http://localhost:8080/api/comments/create/`,data);
            props.refreshFunction(result.data)
            setComment('')
            setErrorMsg('')
        } catch (error) {
          if (typeof error.response.data === 'string') {
            setErrorMsg(error.response.data);
          } else if (error.response.data && typeof error.response.data === 'object') {
              // 객체의 특정 속성을 사용
              const errorMessage = Object.values(error.response.data).join(' ');
              setErrorMsg(errorMessage);
          } else {
              setErrorMsg('An unknown error occurred');
          }
        }
    };
    
      return (  
        <div>
          {accessToken ?( 
            <form style={{ display: 'flex' }}>
              <Box width={"100%"}>
                      <TextField
                          fullWidth
                          label="댓글"
                          type="text"
                          variant="standard"
                          value={comment}
                          onChange={handleChange} 
                          />
              </Box>
              
              <Button style={{ color:'white', width: '30px', height: '47.5px' ,backgroundColor:'#2E7D32'}} onClick={handleAddComment}>저장</Button>
            </form>
          )
          :
            <div style={{display:'flex',color:'red',justifyContent:'center',marginTop:'5rem'}}> 
              댓글을 작성하고 싶으면 로그인 해주세요 ! 
            </div> 
          }
          {msg && 
              <div style={{display:'flex',justifyContent:'center',color:'red'}}>
                {msg}
              </div> 
            }
        </div>  
      ); 


};


export default CreateComment;