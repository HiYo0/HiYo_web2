import { useRef, useState } from "react";

export default function Chatting(props){

    // 1. 해당 컴포넌트가 렌더링 될때 소켓은 재랜더링 방지 useRef
        // useRef(초기값) :             { current : 값 }
        // - 컴포넌트가 렌더링시 참조값을 고정 할수 있다.
    let clientSocket = useRef(null);
    // 2. Ref 참조가 없으면
    if(!clientSocket.current){
        // ===== (클라이언트) 웹소켓 구현 =========================================
            // 1. new WebSocket( 서버소켓URL ); // 비동기
        clientSocket.current = new WebSocket('ws://192.168.17.148:80/chat');
            
        console.log( clientSocket.current );    // 확인
        // onclose // onerror // onmessage // onopen : WebSocket 객체내 포함된 메소드들
            // 2. 각 메소드 정의
                // -1. 클라이언트소켓이 close 되었을때 콜백함수 정의
        clientSocket.current.onclose = (e)=>{console.log(e); console.log('서버소켓 닫음');}
                // -2. 클라이언트소켓이 error 발생했을때 콜백함수 정의 ( * error 이미 발생했고 다음행동 정의 )
        clientSocket.current.onerror = (e)=>{console.log(e); console.log('서버소켓 에러');}
                // -3. 클라이언트소켓이 message 받았을때 콜백함수 정의
        clientSocket.current.onmessage = (e)=>{
            console.log( e ); alert('서버소켓으로부터 메시지 도착');
            console.log('보낸메세지 = '+e.data);
            msgList.push(e.data);
            steMsgList([...msgList]); //setState
            

        }
                // -4. 클라이언트소켓이 open 발생했을때 콜백함수 정의
        clientSocket.current.onopen = (e)=>{console.log(e); console.log("소켓서버와 연결");}
            
            // 연결 종료
        // clientSocket.close();
        // ===== (클라이언트) 웹소켓 구현 END ======================================
    }

    
    const onSend=()=>{
        // 3. 연결된 서버소켓에게 메시지 보내기
        clientSocket.current.send(msgInput);    // 입력받은 데이터를 서버소켓 에게 보내기.
    }
    // - 채팅 내용 입력창
    const [msgInput , setMsgInput] = useState('');
    // - 채팅 창 의 내용 들
    const [msgList , steMsgList] = useState([]);
    


    return(<>
        <div>
            {
                msgList.map((msg)=>{
                    return(<div>
                        {msg}
                    </div>);
                })
            }
            <h3>채팅방</h3>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>

            <button type="button" onClick={onSend}>보내기</button>
        </div>
    </>);
}