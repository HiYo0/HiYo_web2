const scaleName = {c:'섭씨',f:'화씨'};

export default function TemperatureInput(props){
   console.log(props);
    const handleChange = (e)=>{props.onTemperatureChange(e.target.value);}

    return(<>
        <fieldset>
            <div>
                온도를 입력해 주세요(단위 : {scaleName[props.sclale]});
            </div>
            <input type="text" value={props.temperature} onChange={handleChange} />
        </fieldset>
    </>);
}