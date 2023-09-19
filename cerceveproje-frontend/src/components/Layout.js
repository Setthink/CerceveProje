import React from "react";
import Navbar from "./Navbar";
import Box from "@mui/material/Box";

const Layout = ({ children }) => {
  return (
    <Box>
      <Navbar />
      <div sx={{ marginTop: 0, marginLeft: 0, marginRight: 0 }}>{children}</div>
    </Box>
  );
};

export default Layout;
