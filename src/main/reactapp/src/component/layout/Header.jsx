import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { LoginInfoContext } from "../Index";

export default function Header(props){
    // 0. 로그인정보 state변수
    // const [loginInfo , setLoginInfo] = useState('');

    // - provider 컴포넌트의 value 호출
    const { loginInfo , setLoginInfo } = useContext(LoginInfoContext);


    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    // 1. 컴포넌트가 실행될때 1번 axios 요청보내서 회원정보 가져온다
    useEffect(()=>{
        axios.get('/member/login/info/get.do')
        .then((r)=>{ console.log(r);
        setLoginInfo(r.data);
        })
        .catch((e)=>{ console.log(e);})
    } , []);

    function logoutBtn(){
        axios.get('/member/logout/get.do');
        setLoginInfo('');
    }

    // 2. 로그아웃
    const onLogout = () =>{ setLoginInfo('');

        axios.get('/member/logout/get.do')
        .then( r=>{
            if(r.data){alert("로그아웃 성공!!"); window.location.href="/member/login"}
            else{alert("로그아웃 실패");}
        });
        }
    
    return(<>
        <div>
            { loginInfo && <span> {loginInfo.memail} 님</span>}
            { loginInfo && <button type="button" onClick={logoutBtn}>로그아웃</button>}
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li><Link to="/member/login">로그인</Link></li>
                <li><button onClick={onLogout} type="button">로그아웃</button></li>
                <li><Link to="/board/write">글쓰기</Link></li>
                <li><Link to="/board">전체글보기</Link></li>
                <li><Link to="/chat">채팅</Link></li>
            </ul>
        </div>
    </>);
}