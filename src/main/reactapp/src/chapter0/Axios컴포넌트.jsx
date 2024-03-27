import axios from "axios";

export default function Axios컴포넌트(props){
    
    // ====================================================================================== //
    // 1. 기본함수
    function 함수명1(event, 매개변수1){console.log('함수명1  : '); console.log(event);}

    // 2. 화살표함수
    const 함수명2 = ( e ) => { console.log('함수명2  : '); console.log(e); }
    // - 매개변수 포함
    const 함수명3 = ( e , 매개변수1 ) => { console.log('함수명3  : '); console.log( 매개변수1 ); }

    // ====================================================================================== //
    // 1. GET
    const doGet = async ()=>{
        console.log(1);
        await axios.get('https://jsonplaceholder.typicode.com/posts')
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )

        console.log(2);
        await axios.get('https://jsonplaceholder.typicode.com/posts/1')       // path 방식
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
        console.log(3);
        await axios.get('https://jsonplaceholder.typicode.com/comments?postId=1')  // queryString 방식
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
        console.log(4);
        await axios.get('https://jsonplaceholder.typicode.com/comments' , { params:{ 'postId':1 } })  // queryString 방식
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
    }
    
    // 2. POST
    const doPost = ()=>{
        const saveInfo = {
            title: 'foo',
            body: 'bar',
            userId: 1,
        }
        axios.post('https://jsonplaceholder.typicode.com/posts' , saveInfo )
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
        // 2.
        const axioForm = document.querySelector('#axiosForm')
        const axiosFormDate = new FormData(axioForm);
        axios.post('http://localhost:80' , axiosFormDate)       // Content-Type : multipart/from-data;
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )


    }
    // 3. PUT
    const doPut = ()=>{
        const dataInfo = {
            id: 1,
            title: 'foo',
            body: 'bar',
            userId: 1,
        }
        axios.put('https://jsonplaceholder.typicode.com/posts/1',dataInfo)
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
    }

    // 4. DELETE
    const doDelete = ()=>{
        axios.delete('https://jsonplaceholder.typicode.com/posts/1')
            .then( response => {console.log( response ); })
            .catch( error =>{ console.log(error); } )
    }

    return(<>
        <h3>Axios테스트</h3>
        <button type="button" onClick={ 함수명1 }>함수명1 실행</button>
        <button type="button" onClick={ 함수명2 }>함수명2 실행</button>
        <button type="button" onClick={ ( e )=>{ 함수명3( e , 3 ) } }>함수명3 실행</button>
        <button type="button" onClick={ doGet }> doGet테스트 </button>
        <form id="axiosForm">
            <input type="text" />
        </form>
        <button type="button" onClick={ doPost }> doPost테스트 </button>
        <button type="button" onClick={ doPut }> doPut테스트 </button>
        <button type="button" onClick={ doDelete }> doDelete테스트 </button>
    </>);
}