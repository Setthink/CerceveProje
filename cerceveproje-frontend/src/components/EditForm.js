// EditForm.js
import React, { useState } from "react";
import { entityColumns } from "../components/Constants";
import { useParams } from "react-router-dom";
import { useEffect } from "react";
import ApiService from "../api";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
} from "@mui/material";
import { toast } from "react-toastify";

const EditForm = ({ isOpen, onClose, onSave, initialData }) => {
  const { entityType } = useParams();

  const initialFormData = initialData || {}; // Use initialData if provided, or an empty object if not
  const [formData, setFormData] = useState(initialFormData);
  useEffect(() => {
    setFormData(initialData || {});
  }, [initialData]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const renderFormFields = () => {
    return entityColumns[entityType].map((column) => {
      if (column.field !== "id") {
        return (
          <TextField
            key={column.field}
            name={column.field}
            type={column.type}
            label={column.headerName}
            value={formData[column.field]}
            onChange={handleChange}
            fullWidth
            sx={{ my: 2 }}
          />
        );
      }
      return null;
    });
  };

  const handleSave = async (event) => {
    // Add 'rowId' as a parameter
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
      const id = formData.id; // Get the 'rowId' from the form data
      await ApiService.putEntityData(entityType, id, entityData); // Use the 'rowId' parameter

      toast.success(`ID'si ${id} olan ${entityType} başarıyla güncellendi.`, {
        theme: "colored",
      });
      setTimeout(() => {
        navigateToEntityList();
        onClose();
      }, 1500);
    } catch (error) {
      toast.error(error.message);
    }
  };

  const navigateToEntityList = () => {
    window.location.reload();
  };

  return (
    <Dialog open={isOpen} onClose={onClose}>
      <DialogTitle>Güncelle {entityType}</DialogTitle>
      <DialogContent>{renderFormFields()}</DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          İptal
        </Button>
        <Button onClick={handleSave} color="primary">
          Kaydet
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default EditForm;
