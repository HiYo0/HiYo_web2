import axios from "axios"; // axios 라이브러리 호출
import { useState } from "react";

export default function SignUp(props){

    // 1. 상태변수
    const [ memail,setMemail ] = useState('');
    const [ mpassword,setMpassword ] = useState('');
    const [ mname,setMname ] = useState('');

    // 2. memail 수정함수
    const onChangeMemail = (e)=>{
        setMemail(e.target.value);
    }

    // 3. 전송합수
    const onSingUp = (e)=>{
        console.log(memail);console.log(mpassword);console.log(mname);
        /*
        
        */

        let info = {'memail' : memail , 'mpassword' : mpassword , 'mname' : mname}
        axios.post("/member/signup/post.do" , info)
            .then(response => {console.log(response);
                if(response.data==1){
                    alert("회원가입 성공");
                    window.location.href = "/member/login";
                }
                else if(response.data==0){alert("회원가입 실패");}
                else if(response.data==-1){alert("중복된 아이디 입니다.");}
            })
            .catch(error=>{console.log(error);})
        
    }

    return(<>
        <form>
            이메일 : <input type="text" value={memail} onChange={onChangeMemail}/><br />
            패스워드 : <input type="password" value={mpassword} onChange={(e)=>{setMpassword(e.target.value)}}/><br/>
            닉네임 : <input type="text" value={mname} onChange={(e)=>{setMname(e.target.value)}}/><br/>
            <button type="button" onClick={onSingUp}>버튼</button>
        </form>


    </>);
}