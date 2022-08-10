import './App.css';
import Home from './components/Home'
import {Routes, Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Routes>
      <Route path = "/" element = {<Home/>}></Route>
      {/* <Route path = "contact" element = {<Contact/>}></Route> */}
      </Routes>
    </div>
  );
}

export default App;
