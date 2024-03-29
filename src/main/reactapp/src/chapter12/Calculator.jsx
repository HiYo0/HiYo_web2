import { useState } from "react";
import TemperatureInput from "./TemperatureInput";

function BoilingVerdict(props){ // 문구출력
    if(props.celsius >=100 ){
        return <p>물이 끓습니다.</p>
    }
    return <p>물이 끓지 않습니다.</p>
}

function toCelsius(props){ //화씨 계산
    return( ( props - 32 ) * 5 ) / 9;
}

function toFahrenheit(props){ // 섭씨계산
    return (props*9)/5+32;
}

function tryConvert(temperature , convert){
    const input = parseFloat(temperature);
    if(Number.isNaN(input)){return "";}
    const output = convert(input);
    const rounded = Math.round(output * 1000)/1000;
    return rounded.toString();
}

export default function Calculator(props){
    const [temperature , setTemperature] = useState('');
    const [sclale , setScale] = useState('');

    const handleCelsiusChange = (temperature)=>{
        setTemperature(temperature);
        setScale("c");
    };
    const handleFahrenheitChange = (temperature)=>{
        setTemperature(temperature);
        setScale('f');
    };
    const celsius = // f를 입력받은곳엔 섭씨 출력
        sclale === 'f' ? tryConvert(temperature , toCelsius ) : temperature;

    const fahrenheit = // c 를 입력받은곳엔 화씨 출력
        sclale === 'c' ? tryConvert(temperature , toFahrenheit) : temperature;

    return(<>
        <div>
            <TemperatureInput 
            sclale="c" 
            temperature={celsius} 
            onTemperatureChange={handleCelsiusChange}
            />
            <TemperatureInput
            sclale="f"
            temperature={fahrenheit}
            onTemperatureChange={handleFahrenheitChange}
            />
            <BoilingVerdict celsius={parseFloat(celsius)}
            />
        </div>
    </>);
}