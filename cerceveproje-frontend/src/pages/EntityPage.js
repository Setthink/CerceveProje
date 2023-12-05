import React, { useState, useEffect } from "react";
import EditForm from "../components/EditForm";
import { useParams} from "react-router-dom";
import ApiService from "../api";
import DataTable from "../components/DataTable/DataTable"; 
import Layout from "../components/Layout"; 
import { aynaCol, camCol, cerceveCol, musteriCol, paspartuCol, siparisCol } from "../components/constants";
import { toast, ToastContainer,Flip } from "react-toastify";
import ConfirmationDialog from "../components/DialogComponent";
const entityColumns = {
  Ayna: aynaCol,
  Cam: camCol,
  Cerceve: cerceveCol,
  Musteri: musteriCol,
  Paspartu: paspartuCol,
  Siparis: siparisCol,
};

const EntityPage = () => {
  const { entityType } = useParams();

  const [entityData, setEntityData] = useState(null);
  const [siparisData, setSiparisData] = useState(null);
  const [isEditFormOpen, setEditFormOpen] = useState(false);
  const [selectedRowData, setSelectedRowData] = useState(null);
  const [isConfirmationOpen, setIsConfirmationOpen] = useState(false);
  const [selectedRow, setSelectedRow] = useState(null); // Store the selected row for deletion

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await ApiService.getEntityData(entityType);
        const dataWithDate = res.data.map((item) => ({
          ...item,
          siparisTarih: new Date(item.siparisTarih),
        }));
        setEntityData(dataWithDate);
      } catch (err) {
        console.log(err);
      }
    };
    fetchData();
  }, [entityType]);

  const handleEditEntity = (row) => {
    setSelectedRowData(row);
    setEditFormOpen(true);
  };

  const handleSaveEditedData = (editedData) => {
    // Implement logic to save the edited data, e.g., update your data source
    // You can use the 'editedData' and 'selectedRowData' to identify the row to update
    // After saving, update your data source and state accordingly
    // For example:
    const updatedEntityData = entityData.map((row) => {
      if (row.id === selectedRowData.id) {
        return { ...row, ...editedData };
      }
      return row;
    });
  
    setEntityData(updatedEntityData);
  };
  

  const handleDeleteEntity = (row) => {
    console.log(`Delete ${entityType} ID: ${row.id}`);
    setSelectedRow(row);
    setIsConfirmationOpen(true);
  };

  const handleSiparislerEntity = async (row) => {
    try {
      const response = await ApiService.getSiparisByMusteriId(row.id);
      const siparisData = response.data;
      const siparisDataWithDate = siparisData.map((item) => ({
        ...item,
        siparisTarih: new Date(item.siparisTarih),
      }));
      setSiparisData(siparisDataWithDate);
    } catch (error) {
      console.error('Error fetching siparis data:', error);
    }
  };

  const handleConfirmDelete = async (row) => {
    try {
      await ApiService.deleteEntityData(entityType, row.id);
      toast.error(`${entityType} ID ${row.id} has been deleted.`);
      // Refresh the entity list after deletion
      // You can modify this logic based on your application's behavior
      const res = await ApiService.getEntityData(entityType);
      const dataWithDate = res.data.map((item) => ({
        ...item,
        siparisTarih: new Date(item.siparisTarih),
      }));
      setEntityData(dataWithDate);
    } catch (error) {
      toast.error(`Error deleting ${entityType} ID ${row.id}: ${error.message}`);
    }
  };

  return (
    <Layout>
      <ToastContainer 
        autoClose={1500}
        pauseOnFocusLoss={false}
        pauseOnHover={false}
        draggable={false}
        transition={Flip}
        />
      <div>
      {entityData && (
        <DataTable
          rows={entityData}
          columns={entityColumns[entityType]}
          onEdit={handleEditEntity}
          onDelete={handleDeleteEntity}
          onSiparisler={handleSiparislerEntity}
        />
      )}
      {entityType === "Musteri" && siparisData && (
        <DataTable
          rows={siparisData}
          columns={siparisCol}
          onEdit={(row) => handleEditEntity(row)}
          onDelete={(row) => handleDeleteEntity(row)} // Update the onDelete handler for siparis rows
        />
      )}
      {isEditFormOpen && (
          <EditForm
            isOpen={isEditFormOpen}
            onClose={() => setEditFormOpen(false)}
            onSave={handleSaveEditedData}
            initialData={selectedRowData}
          />
        )}
      </div>
      <ConfirmationDialog
        isOpen={isConfirmationOpen}
        onClose={() => setIsConfirmationOpen(false)}
        onConfirm={() => {
          setIsConfirmationOpen(false);
          if (selectedRow) {
            handleConfirmDelete(selectedRow);
          }
        }}
      />
    </Layout>
  );
};

export default EntityPage;