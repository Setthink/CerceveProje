import React, { useState, useEffect } from "react";
import { TextField, Button, Container } from "@mui/material";
import { toast, ToastContainer, Flip } from "react-toastify";
import ApiService from "../api";
import Layout from "../components/Layout";
import { useParams } from "react-router-dom";
import { aynaCol, camCol, cerceveCol, musteriCol, paspartuCol, siparisColPost } from "../components/constants";

const AddEntityPage = () => {
  const { entityType } = useParams();
  const entityColumns = {
    Ayna: aynaCol,
    Cam: camCol,
    Cerceve: cerceveCol,
    Musteri: musteriCol,
    Paspartu: paspartuCol,
    Siparis: siparisColPost,
  };

  const initialFormData = entityColumns[entityType]
    ? entityColumns[entityType].reduce((formData, column) => {
        if (column.field !== "id") {
          formData[column.field] = "";
        }
        return formData;
      }, {En: "", Boy:""})
    : {};

  const [formData, setFormData] = useState(initialFormData);
  const [cerceveData, setCerceveData] = useState(null);
  const [siparisFiyat, setSiparisFiyat] = useState(0);

  useEffect(() => {
    const fetchCerceveData = async () => {
      try {
        const response = await ApiService.getEntityData("cerceve");
        setCerceveData(response.data);
      } catch (error) {
        console.error("Error fetching Cerceve data:", error);
      }
    };

    fetchCerceveData();
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

 const handleCalculateSiparisFiyat = () => {
  // ... (your existing code)
  const cerceveKoduField = entityColumns[entityType].find((column) => column.field === "cerceveKodu");
  const enField = entityColumns[entityType].find((column) => column.field === "en");
  const boyField = entityColumns[entityType].find((column) => column.field === "boy");
  if (cerceveData && cerceveKoduField){
    const cerceveKoduValue = formData[cerceveKoduField.field];
    if(cerceveKoduValue){
      const selectedCerceve = cerceveData.find((cerceve) => cerceve.cerceveKodu === cerceveKoduValue);
      if(selectedCerceve){
        console.log("Selected Cerceve:", selectedCerceve);
        const calculatedFiyat =
        parseFloat(formData[enField.field]) * parseFloat(selectedCerceve.cerceveFiyat) +
        parseFloat(formData[boyField.field]) * parseFloat(selectedCerceve.cerceveFiyat);

      console.log("Calculated Siparis Fiyat:", calculatedFiyat);
      setSiparisFiyat(calculatedFiyat);
      // Optionally, you can update the form data with the calculated siparisFiyat
      setFormData((prevFormData) => ({
        ...prevFormData,
        siparisFiyat: calculatedFiyat,
      }));
    } else {
      const errorMessage = `Bu koda sahip çerçeve bulunmamaktadır : "${cerceveKoduValue}"`;
        // Display toast notification for the error
        toast.error(errorMessage, {
          theme: "colored",
        });
      }
  } else {
    console.log("CerceveKodu is empty");
  }
} else {
  console.log("Invalid entity type or fields for calculating Siparis Fiyat");
}
};
  
  const handleSubmit = async (event) => {
    event.preventDefault();

    const entityData = entityColumns[entityType].reduce((entity, column) => {
      if (column.field !== "id" && formData[column.field]) {
        if (column.field === "siparisFiyat") {
          entity[column.field] = siparisFiyat;
        } else if (column.type === "number") {
          entity[column.field] = parseFloat(formData[column.field]);
        } else if (column.type === "string[]") {
          entity[column.field] = formData[column.field]
            .replace(/\s/g, "")
            .split(",");
        } else {
          entity[column.field] = formData[column.field];
        }
      }
      return entity;
    }, {});

    try {
      await ApiService.postEntityData(entityType, entityData);
      toast.success(`Successfully added ${entityType} data`, {
        theme: "colored",
      });
      setTimeout(() => {
        navigateToEntityList();
      }, 1500);
    } catch (error) {
      toast.error(error.message);
    }
  };

  const renderFormFields = () => {
    return entityColumns[entityType].map((column) => {
      if (column.field !== "id") {
        return (
          <TextField
            key={column.field}
            label={column.headerName}
            name={column.field}
            type={column.type}
            value={formData[column.field]}
            onChange={handleChange}
            placeholder={column.type === "date" ? "yyyy-mm-dd" : ""}
            fullWidth
            required
            sx={{ my: 2 }}
          />
        );
      }
      return null;
    });
  };

  const navigateToEntityList = () => {
    window.location.href = `/${entityType}`;
  };

  if (!entityColumns[entityType]) {
    return (
      <Container maxWidth="sm">
        <div>Invalid entity type: {entityType}</div>
      </Container>
    );
  }

  return (
    <Layout>
      <ToastContainer 
        autoClose={1500}
        pauseOnFocusLoss={false}
        pauseOnHover={false}
        draggable={false}
        transition={Flip}
      />
      <Container maxWidth="sm">
        <form onSubmit={handleSubmit}>
          {renderFormFields()}
          <Button type="submit" variant="contained" color="primary">
            Add {entityType}
          </Button>
          {entityType === "Siparis" && (
          <Button
            variant="contained"
            color="primary"
            onClick={handleCalculateSiparisFiyat}
            sx={{ ml: 2 }}
          > 
            Calculate Siparis Fiyat
          </Button>)}
        </form>
      </Container>
    </Layout>
  );
};

export default AddEntityPage;
