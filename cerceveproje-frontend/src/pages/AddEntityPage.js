import React, { useState } from "react";
import { TextField, Button, Container } from "@mui/material";
import { toast, ToastContainer, Flip } from "react-toastify";
import ApiService from "../api";
import Layout from "../components/Layout";
import { useParams } from "react-router-dom";
import { entityColumnsPost as entityColumns } from "../components/Constants";
import CalculateSiparisFiyat from "../components/CalculateFiyat";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import dayjs from "dayjs";

const AddEntityPage = () => {
  const { entityType } = useParams();

  const initialFormData = entityColumns[entityType]
    ? entityColumns[entityType].reduce(
        (formData, column) => {
          if (column.field !== "id") {
            formData[column.field] = "";
          }
          return formData;
        },
        { En: "", Boy: "" }
      )
    : {};

  const [siparisFiyat, setSiparisFiyat] = useState(0.0);
  const [formData, setFormData] = useState(initialFormData);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleDateChange = (date) => {
    setFormData((prevFormData) => ({
      ...prevFormData,
      siparisTarih: dayjs(date).format("YYYY-MM-DD"), // Send date in the correct format
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const entityData = entityColumns[entityType].reduce((entity, column) => {
      if (
        column.field !== "id" &&
        formData[column.field] !== undefined &&
        formData[column.field] !== null
      ) {
        if (column.field === "siparisFiyat") {
          entity[column.field] = siparisFiyat;
        } else if (column.type === "number") {
          entity[column.field] = parseFloat(formData[column.field]);
        } else if (
          column.type === "string[]" &&
          formData[column.field] === ""
        ) {
          entity[column.field] = [];
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
      toast.error(`${error.response.data.message}`);
    }
  };

  const renderFormFields = () => {
    return entityColumns[entityType].map((column) => {
      if (column.field !== "id") {
        const isSiparisTarih = column.field === "siparisTarih";
        const isSiparisFiyat = column.field === "siparisFiyat";
        const isSiparis = entityType === "Siparis";
        return (
          <React.Fragment key={column.field}>
            {isSiparisTarih ? (
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DemoContainer components={["DatePicker"]}>
                  <DatePicker
                    label={column.headerName}
                    name={column.field}
                    value={
                      formData.siparisTarih
                        ? dayjs(formData.siparisTarih)
                        : dayjs()
                    }
                    onChange={handleDateChange}
                    format="DD/MM/YYYY"
                    textField={(props) => <TextField {...props} fullWidth />}
                  />
                </DemoContainer>
              </LocalizationProvider>
            ) : (
              <TextField
                key={column.field}
                label={column.headerName}
                name={column.field}
                type={column.type}
                value={
                  formData[column.field] ||
                  (column.type === "string[]" ? "" : [])
                }
                onChange={handleChange}
                placeholder={column.type === "date" ? "yyyy-mm-dd" : ""}
                fullWidth
                required={!isSiparis}
                sx={{ my: 2 }}
                disabled={column.field === "siparisFiyat"}
                InputProps={
                  { readOnly: true } &&
                  isSiparisFiyat && {
                    startAdornment: <div style={{ marginRight: "5px" }}>₺</div>,
                  }
                }
              />
            )}
          </React.Fragment>
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
          <Button
            type="submit"
            variant="contained"
            color="primary"
            sx={{ marginRight: 3 }}
          >
            Add {entityType}
          </Button>

          {entityType === "Siparis" && (
            <CalculateSiparisFiyat
              entityType={entityType}
              entityColumns={entityColumns}
              formData={formData}
              setSiparisFiyat={setSiparisFiyat}
              setFormData={setFormData}
            />
          )}
        </form>
      </Container>
    </Layout>
  );
};

export default AddEntityPage;
