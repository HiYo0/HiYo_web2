
export default function AttendanceBook(props){

    // 1. 샘플 데이터
    const students = [
        { id : 1 , name : 'Inje'},
        { id : 2 , name : 'Steve'},
        { id : 3 , name : 'Bill'},
        { id : 4 , name : 'Jeff'}]

    return(<>
        <ul>
            {
            // JSX 시작----------------------

            students.map(( student )=>{
                return(
                <li 
                    key={student.id} // key 속성 추가
                    id={student.id} 
                    className={student.id} 
                >
                    {student.name}
                </li>
                )
            })

            // JSX 끝 -----------------------
            }
        </ul>
    </>);

}