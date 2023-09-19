import React from "react";
import { Dialog, DialogActions, DialogContent, DialogContentText, Button } from "@mui/material";

const ConfirmationDialog = ({ isOpen, onClose, onConfirm }) => {
  return (
    <Dialog open={isOpen} onClose={onClose}>
      <DialogContent>
        <DialogContentText>
          Silmek istediğinize emin misiniz ?
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          İptal Et
        </Button>
        <Button onClick={onConfirm} color="error" >
          SİL
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ConfirmationDialog;
