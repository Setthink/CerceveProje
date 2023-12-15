import React from "react";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { Button } from "@mui/material";
import ButtonGroup from "@mui/material/ButtonGroup";
import "./DataTable.css";

const DataTable = ({ rows, columns, onEdit, onDelete, onSiparisler }) => {
  const actionColumn = {
    field: "İşlemler",
    width: 310,
    renderCell: (buttonn) => (
      <ButtonGroup variant="outlined" color="primary">
        <Button color="success" onClick={() => onEdit(buttonn.row)}>
          Güncelle
        </Button>
        <Button color="error" onClick={() => onDelete(buttonn.row)}>
          Sil
        </Button>
        {buttonn.row.musteriAdi && (
          <Button onClick={() => onSiparisler(buttonn.row)}>Siparisler</Button>
        )}
      </ButtonGroup>
    ),
  };

  const columnsWithActions = [...columns, actionColumn];

  return (
    <div style={{ textAlign: "center", paddingTop: "10px", width: "100%" }}>
      <DataGrid
        rows={rows}
        columns={columnsWithActions}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 10,
            },
          },
        }}
        slots={{ toolbar: GridToolbar }}
        slotProps={{
          toolbar: {
            showQuickFilter: true,
            quickFilterProps: { debounceMs: 500 },
          },
        }}
        pageSizeOptions={[5, 10]}
        disableRowSelectionOnClick
        disableColumnFilter
        disableDensitySelector
        disableColumnSelector
        disableColumnMenu
      />
    </div>
  );
};

export default DataTable;
