import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Menu,
  MenuItem,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import { Link } from "react-router-dom";
import React from "react";

const Navbar = () => {
  const [musteriAnchorEl, setMusteriAnchorEl] = React.useState(null);
  const [siparisAnchorEl, setSiparisAnchorEl] = React.useState(null);
  const [cerceveAnchorEl, setCerceveAnchorEl] = React.useState(null);
  const [camAnchorEl, setCamAnchorEl] = React.useState(null);
  const [paspartuAnchorEl, setPaspartuAnchorEl] = React.useState(null);
  const [aynaAnchorEl, setAynaAnchorEl] = React.useState(null);

  const handleMusteriClick = (event) => {
    setMusteriAnchorEl(event.currentTarget);
  };

  const handleSiparisClick = (event) => {
    setSiparisAnchorEl(event.currentTarget);
  };

  const handleCerceveClick = (event) => {
    setCerceveAnchorEl(event.currentTarget);
  };

  const handleCamClick = (event) => {
    setCamAnchorEl(event.currentTarget);
  };

  const handlePaspartuClick = (event) => {
    setPaspartuAnchorEl(event.currentTarget);
  };

  const handleAynaClick = (event) => {
    setAynaAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setMusteriAnchorEl(null);
    setSiparisAnchorEl(null);
    setCerceveAnchorEl(null);
    setCamAnchorEl(null);
    setPaspartuAnchorEl(null);
    setAynaAnchorEl(null);
  };

  return (
    <AppBar position="static" sx={{ borderRadius: "6px" }}>
      <Toolbar>
        <Button
          color="inherit"
          component={Link}
          to={"/"}
          size="large"
          sx={{ mr: 2 }}
          startIcon={<HomeIcon />}
        ></Button>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Elif Çerçeve
        </Typography>

        {/* Siparis Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handleSiparisClick}>
          Siparis
        </Button>
        <Menu
          anchorEl={siparisAnchorEl}
          open={Boolean(siparisAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Siparis"}>
            Siparis Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Siparis"}>
            Tüm Siparisler
          </MenuItem>
        </Menu>

        {/* Musteri Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handleMusteriClick}>
          Müşteri
        </Button>
        <Menu
          anchorEl={musteriAnchorEl}
          open={Boolean(musteriAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Musteri"}>
            Müşteri Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Musteri"}>
            Tüm Müşteriler
          </MenuItem>
        </Menu>

        {/* Cerceve Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handleCerceveClick}>
          Cerceve
        </Button>
        <Menu
          anchorEl={cerceveAnchorEl}
          open={Boolean(cerceveAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Cerceve"}>
            Cerceve Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Cerceve"}>
            Tüm Cerceveler
          </MenuItem>
        </Menu>

        {/* Cam Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handleCamClick}>
          Cam
        </Button>
        <Menu
          anchorEl={camAnchorEl}
          open={Boolean(camAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Cam"}>
            Cam Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Cam"}>
            Tüm Camlar
          </MenuItem>
        </Menu>

        {/* Paspartu Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handlePaspartuClick}>
          Paspartu
        </Button>
        <Menu
          anchorEl={paspartuAnchorEl}
          open={Boolean(paspartuAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Paspartu"}>
            Paspartu Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Paspartu"}>
            Tüm Paspartular
          </MenuItem>
        </Menu>

        {/* Ayna Navbar with a dropdown menu */}
        <Button color="inherit" onClick={handleAynaClick}>
          Ayna
        </Button>
        <Menu
          anchorEl={aynaAnchorEl}
          open={Boolean(aynaAnchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} component={Link} to={"/add/Ayna"}>
            Ayna Ekle
          </MenuItem>
          <MenuItem onClick={handleClose} component={Link} to={"/Ayna"}>
            Tüm Aynalar
          </MenuItem>
        </Menu>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
