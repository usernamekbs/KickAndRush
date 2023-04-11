import React, { useState } from "react";
import service from "../../services/categoryService";
import { Input,Button,Form } from 'antd';
import { useNavigate,Link } from 'react-router-dom';

const { TextArea } = Input;

const AddCategory = (props) => {
    const [title, setTitle] = useState("");   
    const [url, setUrl] = useState("");   

    const onSubmit = (e) => {
      e.preventDefault();

      const newCategory = {
        title : title,
        url : url
      };
  
      service.addCategory(newCategory)
        .then(response => {
            props.refreshFunction(response.data)
            setTitle("")
            setUrl("")
        })
        .catch(e => {
          console.log(e); 
        });
     };

    return (  
        <div style={{marginTop:'7%'}}>
            <TextArea placeholder="제목을 작성해주세요." autoSize value={title}  onChange={e => setTitle(e.target.value)} />
            <div
                style={{
                margin: '24px 0',
                }}
            />
            <TextArea placeholder="URL을 작성해주세요." autoSize value={url}  onChange={e => setUrl(e.target.value)} />
            <div
                style={{
                margin: '24px 0',
                }}
            />
            <Form.Item style={{marginTop:20}}>
                <Button type="primary" onClick={onSubmit}>
                    저장
                </Button>
            </Form.Item>
        </div>  
    ); 
};
  
  
export default AddCategory;