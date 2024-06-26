import { useContext } from "react";
import ThemeContext from "./ThemeContext";

export default function MainContent(props){

    // 1. 
    const { theme , toggleTheme } = useContext(ThemeContext);
    // 2.
    return(
        <div 
            style={{
                width: "100vw",
                height : "100vw",
                padding : "1.5rem",
                backgroundColor : theme == "light"?"white":"black",
                color : theme == "light"?"black":"white",
            }}
        >
            <p>안녕하세요, 테마 변경이 가능한 웹사이트 입니다.</p>
            <button onClick={toggleTheme}>테마 변경</button>

        </div>
    );
}