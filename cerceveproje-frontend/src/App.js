import React from "react";
import Home from "./pages/Home";
import Layout from "./components/Layout";
import 'react-toastify/dist/ReactToastify.css';
import "./components/DataTable/DataTable.css";

const App = () => {


  return (
    <Layout>
      <Home />
    </Layout>
  );
};

export default App;
