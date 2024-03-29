import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Header(props){
    // 0. 로그인정보 state변수
    const [loginInfo , setloginInfo] = useState('');

    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    // 1. 컴포넌트가 실행될때 1번 axios 요청보내서 회원정보 가져온다
    useEffect(()=>{
        axios.get('/member/login/info/get.do')
        .then((r)=>{ console.log(r);
        setloginInfo(r.data);
        })
        .catch((e)=>{ console.log(e);})
    } , []);

    function logoutBtn(){
        axios.get('/member/logout/get.do');
        setloginInfo('');
    }
    
    return(<>
        <div>
            { loginInfo && <span> {loginInfo.memail} 님</span>}
            { loginInfo && <button type="button" onClick={logoutBtn}>로그아웃</button>}
            <ul>
                <li><Link to="/">홈</Link></li>
                <li><Link to="/member/signup">회원가입</Link></li>
                <li><Link to="/member/login">로그인</Link></li>
            </ul>
        </div>
    </>);
}