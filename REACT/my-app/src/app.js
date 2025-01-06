import { BrowserRouter, Routes, Route } from "react-router-dom";
import Index1 from "./index1.js";
import Datatable from "./datatable.js";
import './datatable.css';
function App(){
    return (
    <BrowserRouter>
        <Routes>
            <Route path="/index" element={<Index1/>} />
            <Route path="/datatable" element={<Datatable />} />
            <Route path="*" element={<h1>404 - Page Not Found</h1>} />
        </Routes>
    </BrowserRouter>

    );
}
export default App;