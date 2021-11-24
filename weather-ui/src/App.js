import {useState} from 'react'; 


function App() {
  const [ displayData, setDisplayData ] = useState('');
  const [ location, setLocation ]   = useState('');
  return (
    <div className="App">
      <h1> Weather App</h1>
      <h2> Please Enter the Location</h2>
      <form onSubmit={(event) => handleSubmit(event, setDisplayData, location)}> 
        <input type="text" 
        placeholder="Enter your The Location Here"
        onChange= { (event) => handleChange(event,setLocation,) }  />
        <button>Submit</button>
      </form>
      
    {  displayData && <h2> The Current Temperature is {displayData} F</h2>}
    </div> 
    
  );
}
function handleChange(event, setDisplayData) {setDisplayData(event.target.value);}

function handleSubmit(event, setDisplayData, location) {
  event.preventDefault();
  if (location && location.length > 0) {
    getWeather(location,setDisplayData);
  }
}
function handleErrors(response) {
  if (!response.ok) {
      alert("Please Enter a valid Location");
  }
  return response;
}
function getWeather(location, setDisplayData) {

  fetch('http://localhost:8080/v1/weather-data/' + location)
  .then(handleErrors)
  .then(response => { console.log(response); return response.json() })
  .then(data => { 
    console.log(data);
    var currentTeperature = data.currentTemperature;
  
  setDisplayData(currentTeperature);}).catch(err => console.log(err));
}


export default App;