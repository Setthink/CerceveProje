import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { TextField, Button, Container } from "@mui/material";
import { aynaCol, camCol, cerceveCol, musteriCol, paspartuCol, siparisColPost } from "../components/constants";
import Layout from "../components/Layout";
import { toast,ToastContainer,Flip} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import ApiService from "../api";

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
      }, {})
    : {};

  const [formData, setFormData] = useState(initialFormData);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
  
    const entityData = entityColumns[entityType].reduce((entity, column) => {
      if (column.field !== "id" && formData[column.field]) {
        if (column.type === "number") {
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
      // Call the postEntityData function to send the data to the API
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
            type={column.type} // This sets the input type based on the column type
            value={formData[column.field]}
            onChange={handleChange}
            placeholder={column.type === "date" ? "yyyy-mm-dd" : ""}
            fullWidth
            required
            sx={{ my: 2 }} // Add spacing between fields
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
        </form>
        </Container>
    </Layout>
  );
};

export default AddEntityPage;
