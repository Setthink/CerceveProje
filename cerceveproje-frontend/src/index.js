import React from "react";
import App from "./App";
import { createRoot } from 'react-dom/client';
import { ThemeProvider, createTheme } from "@mui/material";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddEntityPage from "./pages/AddEntityPage";
import EntityPage from "./pages/EntityPage";

const theme = createTheme({
  components: {
    MuiDataGrid: {
      GridColumnHeaderTitle: {
        fontSize: "32px",
      },
    },
  },
});



const rootElement = document.getElementById('root');

createRoot(rootElement).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="/add/:entityType" element={<AddEntityPage />} />
          <Route path=":entityType" element={<EntityPage />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>
);