import React, { useEffect,useState } from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import axios from 'axios';

const CallbackPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const loginToServer = async () => {
        try {
          const params = new URLSearchParams(location.search);
          const code = params.get('code');
          const state = params.get('state');
          const response = await axios.get('http://localhost:8080/api/user/callback', {
            params: {
              code: code,
              state: state
            }
          });
          
          localStorage.setItem('expiresIn', response.data.expiresIn); 
          localStorage.setItem('accessToken', response.data.accessToken);
          localStorage.setItem('name',response.data.name)
          localStorage.setItem('email',response.data.email)
          localStorage.setItem('userId',response.data.userId)
          localStorage.setItem('role',response.data.role)
          navigate('/category/3/post')
        } catch (error) {
          console.error('Error occurred while logging in:', error);
        }
      };
      loginToServer();
  }, [location]);

  return (
    <div>
      네이버 로그인 인증 중...
    </div>
  );
};

export default CallbackPage;