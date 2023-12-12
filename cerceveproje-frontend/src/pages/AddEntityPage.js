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

  
  const [camData, setCamData] = useState(null);
  const [paspartuData, setPaspartuData] = useState(null);  
  const [formData, setFormData] = useState(initialFormData);
  const [cerceveData, setCerceveData] = useState(null);
  const [siparisFiyat, setSiparisFiyat] = useState(0.0);

  useEffect(() => {
    const fetchData = async (entityType) => {
      try {
        const response = await ApiService.getEntityData(entityType);
        switch (entityType) {
          case "cerceve":
            setCerceveData(response.data);
            break;
          case "cam":
            setCamData(response.data);
            break;
          case "paspartu":
            setPaspartuData(response.data);
            break;
          // Add more cases if you have other entities to fetch
          default:
            break;
        }
      } catch (error) {
        console.error(`Error fetching ${entityType} data:`, error);
      }
    };
  
    const fetchAllData = async () => {
      await fetchData("cerceve");
      await fetchData("cam");
      await fetchData("paspartu");
      // Add more entities if needed
    };
  
    fetchAllData();
  }, []);
  

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleCalculateSiparisFiyat = () => {
    const cerceveKoduField = entityColumns[entityType].find((column) => column.field === "cerceveKodu");
    const camKoduField = entityColumns[entityType].find((column) => column.field === "camKodu");
    const paspartuKoduField = entityColumns[entityType].find((column) => column.field === "paspartuKodu");
    const enField = entityColumns[entityType].find((column) => column.field === "en");
    const boyField = entityColumns[entityType].find((column) => column.field === "boy");
  
    if (cerceveData && cerceveKoduField && camKoduField && paspartuKoduField) {
      // Split the values for each field
      const cerceveKoduValues = formData[cerceveKoduField.field].split(',').map(value => value.trim());
      const firstCerceve = cerceveData.find((cerceve) => cerceve.cerceveKodu === cerceveKoduValues[0]);
      const camKoduValues = formData[camKoduField.field].split(',').map(value => value.trim());
      const paspartuKoduValues = formData[paspartuKoduField.field].split(',').map(value => value.trim());
  
      console.log("en:", formData[enField.field]);
      console.log("boy:", formData[boyField.field]);
      // Calculate siparisFiyat for cerceveKoduValues
      const calculatedFiyatCerceve = cerceveKoduValues.reduce((totalFiyat, cerceveKoduValue) => {
        const selectedCerceve = cerceveData.find((cerceve) => cerceve.cerceveKodu === cerceveKoduValue);
        if (selectedCerceve) {
          console.log("Selected Cerceve:", selectedCerceve);
          const enFiyatCerceve = parseFloat(formData[enField.field]) * parseFloat(selectedCerceve.cerceveFiyat);
          const boyFiyatCerceve = parseFloat(formData[boyField.field]) * parseFloat(selectedCerceve.cerceveFiyat);
          console.log("enFiyatCerceve:", enFiyatCerceve);
          console.log("boyFiyatCerceve:", boyFiyatCerceve);
          console.log("deneme : ", firstCerceve.cerceveGenislik);
        
          const genislikFiyatCerceve =((parseFloat(formData[enField.field]) * parseFloat(selectedCerceve.cerceveGenislik)));
        
          console.log("formData[enField.field]:", formData[enField.field]);
          console.log("formData[cerceveKoduField.field]:", formData[cerceveKoduField.field]);
          console.log("selectedCerceve.cerceveGenislikFiyat:", selectedCerceve.cerceveGenislik);
          console.log("genislikFiyatCerceve:", genislikFiyatCerceve);
        
          return totalFiyat + enFiyatCerceve + boyFiyatCerceve + genislikFiyatCerceve;
        }  else {
          const errorMessage = `Bu koda sahip cerceve bulunamadı: "${cerceveKoduValue}"`;
          toast.error(errorMessage, {
            theme: "colored",
          });
          console.log(totalFiyat);
          return totalFiyat;
        }
      }, 0);

      const calculatedFiyatCam = 31;
      const calculatedFiyatPaspartu = 32;
      
/*       const calculatedFiyatPaspartu = paspartuKoduValues.reduce((totalFiyat, paspartuKoduValue) => {
        const selectedPaspartu = paspartuData.find((paspartu) => paspartu.paspartuKodu === paspartuKoduValue);
        if (selectedPaspartu) {
          // Your logic for paspartu
          const enFiyatPaspartu = parseFloat(formData[enField.field]) * parseFloat(selectedPaspartu.paspartuFiyat);
          const boyFiyatPaspartu = parseFloat(formData[boyField.field]) * parseFloat(selectedPaspartu.paspartuFiyat);
      
          return totalFiyat + enFiyatPaspartu + boyFiyatPaspartu;
        } else {
          const errorMessage = `Bu koda sahip paspartu bulunamadı: "${paspartuKoduValue}"`;
          toast.error(errorMessage, {
            theme: "colored",
          });
          console.log(totalFiyat);
          return totalFiyat;
        }
      }, 0);
      
      // Calculate siparisFiyat for camKoduValues
      const calculatedFiyatCam = camKoduValues.reduce((totalFiyat, camKoduValue) => {
        const selectedCam = camData.find((cam) => cam.camKodu === camKoduValue);
        if (selectedCam) {
          // Your logic for cam
          const enFiyatCam = parseFloat(formData[enField.field]) * parseFloat(selectedCam.camFiyat);
          const boyFiyatCam = parseFloat(formData[boyField.field]) * parseFloat(selectedCam.camFiyat);
      
          return totalFiyat + enFiyatCam + boyFiyatCam;
        } else {
          const errorMessage = `Bu koda sahip cam bulunamadı: "${camKoduValue}"`;
          toast.error(errorMessage, {
            theme: "colored",
          });
          console.log(totalFiyat);
          return totalFiyat;
        }
      }, 0); */
      // Calculate total siparisFiyat by summing up individual calculated values
      const totalCalculatedFiyat = calculatedFiyatCerceve + calculatedFiyatCam + calculatedFiyatPaspartu;
  
      console.log("Total Calculated Siparis Fiyat:", totalCalculatedFiyat);
      setSiparisFiyat(totalCalculatedFiyat);
      setFormData((prevFormData) => ({
        ...prevFormData,
        siparisFiyat: totalCalculatedFiyat,
      }));
    } else {
      const errorMessage = "Invalid entity type or fields for calculating Siparis Fiyat";
      toast.error(errorMessage, {
        theme: "colored",
      });
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
            readOnly={column.field === "siparisFiyat"}
            disabled={column.field === "siparisFiyat"}
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
