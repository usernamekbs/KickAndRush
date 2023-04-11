import React, { useState,useEffect } from "react";
import service from "../../services/categoryService";
import { FolderOutlined } from '@ant-design/icons';
import { Button } from 'antd'
import TextArea from "antd/lib/input/TextArea";

const Category = ({props,setCategory,delCategory,updCategory}) => {
    const [edit,setEdit] = useState(false) 
    const [title,setTitle] = useState(props.title)
    const [url]  = useState(props.url)
     
    const deleteCategory = (ctg_cno) =>{
        
        service.deleteCategory(ctg_cno).then(response => {
            setCategory((prevCategory) => 
                prevCategory.map( category => { return {...category} }).filter ( category => {
                    return category.ctg_cno !== ctg_cno
                })    
            )
            delCategory(ctg_cno)
        })
        .catch(e => {  
            console.log(e);
        });  
    }

    const updateCategory = (ctg_cno) =>{
        console.log(ctg_cno)
        const newCategory = {
            ctg_cno : ctg_cno,
            title : title,
            url : url
        };
        service.updateCategory(newCategory).then(response => {
            setCategory((prevCategory) => 
                prevCategory.map((c)=>{
                    if(ctg_cno === c.ctg_cno){
                        return {...c,title : response.data.title}
                    }
                    return c
                })    
            )
            updCategory(response.data)
            setEdit(false)
        })
        .catch(e => {  
            console.log(e);
        });  
    }

    return ( 
        <>
            {edit === false ?
            <>
                <FolderOutlined style={{marginRight:15}}/>
                {props.title}
                <Button style={{marginLeft:20,backgroundColor:'#008000',color:'white'}} onClick={() => setEdit((show) => !show)}>수정</Button>
                <Button style={{marginLeft:20,backgroundColor:'#ff4d4f',color:'white'}} onClick={() => deleteCategory(props.ctg_cno)}>삭제</Button>
            </>
            :
            <>
                <FolderOutlined style={{marginRight:10}}/>
                <TextArea placeholder="제목을 작성해주세요." autoSize value={title}  onChange={e => setTitle(e.target.value)} />
                <Button style={{marginLeft:20,backgroundColor:'#008000',color:'white'}} onClick={() => updateCategory(props.ctg_cno)}>완료</Button>
                <Button style={{marginLeft:20,backgroundColor:'#ff4d4f',color:'white'}} onClick={() => deleteCategory(props.ctg_cno)}>삭제</Button> 
            </>
            }
        </>
    ); 
};
  
  
export default Category;