import { useState } from "react";

export default function SignUp(props){
    // 이름입력받기위한 변수
    const [ name,setName ] = useState('');
    // 성별 입력받기위한 변수
    const [ gender , setGender ] = useState('남자');

    // 이름 입력 칸이 변경될때 함수
    const handleChangeName = (e)=>{
        // 이벤트의 타겟 : 인풋? 의 value 값을 수정
        setName(e.target.value);
    };
    // 성별 칸이 변경될때 함수
    const handleChangeGender = (e)=>{
        // 이벤트의 타겟 : 인풋? 의 value 값을 수정
        setGender(e.target.value);
    };

    // 출력함수
    const handleSubmit = (e)=>{
        // 제출 누르면 알람으로 이름 출력
        alert(`이름 : ${name} , 성별 : ${gender}`);
    };


    return(
        <form>
            <div>
                이름 : <input type="text" value={name} onChange={handleChangeName} /><br />
            </div>
            <div>
                성별 : <select onChange={handleChangeGender}>
                    <option value="남자">남자</option>
                    <option value="여자">여자</option>
                </select>
            </div>
            <button type="button" onClick={handleSubmit}>제출버튼</button>
        </form>
    );
}