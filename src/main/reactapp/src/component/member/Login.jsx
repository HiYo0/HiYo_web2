import axios from "axios";

export default function Login(props){

    const onLogin = ()=>{
        // 1. 전송할 폼 가져오기
        const loginFrom = document.querySelector('#loginFrom');
        // 2. 데이터폼 으로 변환
        const loginFromData = new FormData(loginFrom);
        // 3. 서버와 통신
        axios.post('/member/login/post.do',loginFromData)
        .then((response)=>{ console.log(response);
            if(response.data){
                alert('로그인성공');
            }else{alert("로그인실패");}
            window.location.href="/";

        })
        .catch((e)=>{console.log(e);})

    }

    return(<>
        <form id="loginFrom">
            이메일 : <input type="text" name="memail" />
            비밀번호 : <input type="password" name="mpassword" />
            <button type="button" onClick={ onLogin }>로그인</button>
        </form>
    </>);
}