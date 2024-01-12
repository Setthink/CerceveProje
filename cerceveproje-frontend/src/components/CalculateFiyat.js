import React, { useState, useCallback, useEffect } from "react";
import { toast } from "react-toastify";
import ApiService from "../api";
import { Button } from "@mui/material";

const CalculateSiparisFiyat = ({
  entityType,
  entityColumns,
  formData,
  setSiparisFiyat,
  setFormData,
}) => {
  const [cerceveData, setCerceveData] = useState([]);
  const [camData, setCamData] = useState([]);
  const [paspartuData, setPaspartuData] = useState([]);
  const [aynaData, setAynaData] = useState([]);

  const fetchData = useCallback(async (entityType) => {
    try {
      const response = await ApiService.getEntityData(entityType);
      switch (entityType) {
        case "cerceve":
          setCerceveData(response.data || []);
          break;
        case "cam":
          setCamData(response.data || []);
          break;
        case "paspartu":
          setPaspartuData(response.data || []);
          break;
        case "ayna":
          setAynaData(response.data || []);
          break;
        default:
          break;
      }
    } catch (error) {
      toast.error(`Error fetching ${entityType} data: ${error.message}`, {
        theme: "colored",
      });
    }
  }, []);

  useEffect(() => {
    const fetchAllData = async () => {
      await fetchData("cerceve");
      await fetchData("cam");
      await fetchData("paspartu");
      await fetchData("ayna");
    };

    fetchAllData();
  }, [fetchData]);

  const handleCalculateSiparisFiyat = useCallback(() => {
    const cerceveKoduField = entityColumns[entityType]?.find(
      (column) => column.field === "cerceveKodu"
    );
    const camKoduField = entityColumns[entityType]?.find(
      (column) => column.field === "camKodu"
    );
    const paspartuKoduField = entityColumns[entityType]?.find(
      (column) => column.field === "paspartuKodu"
    );
    const aynaKoduField = entityColumns[entityType]?.find(
      (column) => column.field === "aynaKodu"
    );
    const enField = entityColumns[entityType]?.find(
      (column) => column.field === "en"
    );
    const boyField = entityColumns[entityType]?.find(
      (column) => column.field === "boy"
    );

    if (
      cerceveKoduField &&
      camKoduField &&
      paspartuKoduField &&
      aynaKoduField
    ) {
      const cerceveKoduValues = formData[cerceveKoduField.field]
        .split(",")
        .map((value) => value.trim());
      const firstCerceve = cerceveData.find(
        (cerceve) => cerceve.cerceveKodu === cerceveKoduValues[0]
      );
      const camKoduValues = formData[camKoduField.field]
        .split(",")
        .map((value) => value.trim());
      const paspartuKoduValues = formData[paspartuKoduField.field]
        .split(",")
        .map((value) => value.trim());
      const aynaKoduValue = formData[aynaKoduField.field]
        .split(",")
        .map((value) => value.trim());
      const calculatedFiyatCerceve = cerceveKoduValues.reduce(
        (totalFiyat, cerceveKoduValue) => {
          const selectedCerceve = cerceveData.find(
            (cerceve) => cerceve.cerceveKodu === cerceveKoduValue
          );
          if(cerceveKoduValues[0] === ""){
            return totalFiyat;
          }
          const en = parseFloat(formData[enField.field]) + selectedCerceve.cerceveGenislik * 2;
          const boy = parseFloat(formData[boyField.field]) + selectedCerceve.cerceveGenislik * 2;
          const firstCerceveGenislik = parseFloat(firstCerceve.cerceveGenislik);

          if (selectedCerceve) {
            if(selectedCerceve.cerceveKodu === cerceveKoduValues[0]){
              const cerceveFiyat =
                (en  + boy) * 2 * (selectedCerceve.cerceveFiyat/100);
              return totalFiyat + cerceveFiyat;
            }
            if (
              cerceveKoduValues.length === 2 &&
              selectedCerceve.cerceveKodu === cerceveKoduValues[1]
            ) {
              const cerceveFiyat =
              (((en + firstCerceveGenislik) * 2) +
              ((boy + firstCerceveGenislik) * 2)) *
              2 * (selectedCerceve.cerceveFiyat/100);
              return totalFiyat + cerceveFiyat;
            }
            return totalFiyat;
          } else {
            const errorMessage = `Bu koda sahip cerceve bulunamadı: "${cerceveKoduValue}"`;
            toast.error(errorMessage, {
              theme: "colored",
            });
            return totalFiyat;
          }
        },
        0
      );

      const calculatedFiyatPaspartu = paspartuKoduValues.reduce(
        (totalFiyat, paspartuKoduValue) => {
          const selectedPaspartu = paspartuData.find(
            (paspartu) => paspartu.paspartuKodu === paspartuKoduValue
          );
          if(paspartuKoduValues[0] === ""){
            return totalFiyat;
          }
          const en = parseFloat(formData[enField.field]);
          const boy = parseFloat(formData[boyField.field]);
          if (selectedPaspartu) {
            const paspartuFiyat =
              en * boy * parseFloat(selectedPaspartu.paspartuFiyat)/10000;
            return totalFiyat + paspartuFiyat;
          } else {
            const errorMessage = `Bu koda sahip paspartu bulunamadı: "${paspartuKoduValue}"`;
            toast.error(errorMessage, {
              theme: "colored",
            });
            return totalFiyat;
          }
        },
        0
      );

      const calculatedFiyatCam = camKoduValues.reduce(
        (totalFiyat, camKoduValue) => {
          const selectedCam = camData.find(
            (cam) => cam.camKodu === camKoduValue
          );
          if(camKoduValues[0] === ""){
            return totalFiyat;
          }
          const en = parseFloat(formData[enField.field]);
          const boy = parseFloat(formData[boyField.field]);

          if (selectedCam) {
            const camFiyat =
              en * boy * parseFloat(selectedCam.camFiyat)/10000;
            return totalFiyat + camFiyat;
          } else {
            const errorMessage = `Bu koda sahip cam bulunamadı: "${camKoduValue}"`;
            toast.error(errorMessage, {
              theme: "colored",
            });
            return totalFiyat;
          }
        },
        0
      );

      const calculatedFiyatAyna = aynaKoduValue.reduce(
        (totalFiyat, aynaKoduValue) => {
          const selectedAyna = aynaData.find(
            (ayna) => ayna.aynaKodu === aynaKoduValue
          );
          if(aynaKoduValue === ""){
            return totalFiyat;
          }
          const en = parseFloat(formData[enField.field]);
          const boy = parseFloat(formData[boyField.field]);
          if (selectedAyna) {
            const aynaFiyat =
              en * boy * parseFloat(selectedAyna.aynaFiyat)/10000;
            return totalFiyat + aynaFiyat;
          } else {
            const errorMessage = `Bu koda sahip ayna bulunamadı: "${aynaKoduValue}"`;
            toast.error(errorMessage, {
              theme: "colored",
            });
            return totalFiyat;
          }
        },
        0
      );

      const totalCalculatedFiyat =
        calculatedFiyatCerceve +
        calculatedFiyatCam +
        calculatedFiyatPaspartu +
        calculatedFiyatAyna;
      setSiparisFiyat(totalCalculatedFiyat);

      setFormData((prevFormData) => ({
        ...prevFormData,
        siparisFiyat: totalCalculatedFiyat,
      }));
    } else {
      const errorMessage =
        "Invalid entity type or fields for calculating Siparis Fiyat";
      toast.error(errorMessage, {
        theme: "colored",
      });
    }
  }, [
    entityType,
    entityColumns,
    formData,
    setSiparisFiyat,
    setFormData,
    cerceveData,
    camData,
    paspartuData,
    aynaData,
  ]);

  return (
    <Button
      onClick={handleCalculateSiparisFiyat}
      variant="contained"
      color="primary"
    >
      Siparişin Fiyatını Hesapla
    </Button>
  );
};

export default CalculateSiparisFiyat;
