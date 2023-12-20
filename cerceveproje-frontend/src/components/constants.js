const aynaCol = [
  { field: "id", headerName: "ID" },
  { field: "aynaKodu", type: "string", headerName: "Ayna Kodu", width: 200 },
  {
    field: "aynaFiyat",
    headerName: "Ayna Fiyatı",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 250,
  },
];

const camCol = [
  { field: "id", headerName: "ID" },
  { field: "camKodu", type: "string", headerName: "Cam Kodu", width: 200 },
  { field: "camModel", type: "string", headerName: "Cam Model", width: 200 },
  {
    field: "camFiyat",
    headerName: "Cam Fiyatı",
    type: "number",
    align: "left",
    headerAlign: "left",
    width: 250,
  },
];

const cerceveCol = [
  { field: "id", headerName: "ID" },
  {
    field: "cerceveKodu",
    type: "string",
    headerName: "Cerceve Kodu",
    width: 200,
  },
  {
    field: "cerceveBoyutu",
    headerName: "Çerceve Boyut",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 250,
  },
  {
    field: "cerceveGenislik",
    headerName: "Çerceve Genislik",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 250,
  },
  {
    field: "cerceveFiyat",
    headerName: "Çerceve Fiyatı",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 250,
  },
];

const musteriCol = [
  { field: "id", headerName: "ID" },
  { field: "musteriAdi", headerName: "Müşteri Adı", width: 200 },
  { field: "musteriNumara", headerName: "Müşteri Numarası", width: 250 },
];
const paspartuCol = [
  { field: "id", headerName: "ID" },
  {
    field: "paspartuKodu",
    type: "string",
    headerName: "Paspartu Kodu",
    width: 200,
  },
  {
    field: "paspartuFiyat",
    headerName: "Paspartu Fiyatı",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 250,
  },
];

const siparisCol = [
  { field: "id", headerName: "ID", width: 75 },
  {
    field: "en",
    headerName: "En",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 75,
  },
  {
    field: "boy",
    headerName: "Boy",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 75,
  },
  {
    field: "cerceveler",
    headerName: "Çerçeveler",
    type: "string[]",
    width: 120,
    valueGetter: (params) => {
      return (
        params.row.cerceveler
          ?.map((cerceve) => cerceve.cerceveKodu)
          .join(", ") || ""
      );
    },
  },
  {
    field: "paspartular",
    headerName: "Paspartular",
    type: "string[]",
    width: 130,
    valueGetter: (params) => {
      return (
        params.row.paspartular
          ?.map((paspartu) => paspartu.paspartuKodu)
          .join(", ") || ""
      );
    },
  },
  {
    field: "camlar",
    headerName: "Camlar",
    type: "string[]",
    width: 120,
    valueGetter: (params) => {
      return params.row.camlar?.map((cam) => cam.camKodu).join(", ") || "";
    },
  },
  {
    field: "ayna",
    headerName: "Ayna",
    width: 100,
    valueGetter: (params) => {
      return params.row.ayna ? params.row.ayna.aynaKodu : "";
    },
  },
  {
    field: "musteri",
    headerName: "Müşteri Adı",
    width: 150,
    valueGetter: (params) => {
      return params.row.musteri ? params.row.musteri.musteriAdi : "";
    },
  },
  {
    field: "siparisTarih",
    headerName: "Sipariş Tarihi",
    type: "date",
    width: 150,
  },
  { field: "siparisNot", headerName: "Sipariş Not", width: 200 },
  {
    field: "siparisFiyat",
    headerName: "Sipariş Fiyatı",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 150,
  },
];

const siparisColPost = [
  { field: "id", headerName: "ID", width: 75 },
  { field: "en", headerName: "En", type: "number", width: 75 },
  { field: "boy", headerName: "Boy", type: "number", width: 75 },
  {
    field: "cerceveKodu",
    headerName: "Çerçeveler",
    type: "string[]",
    width: 120,
  },
  {
    field: "paspartuKodu",
    headerName: "Paspartular",
    type: "string[]",
    width: 130,
  },
  {
    field: "camKodu",
    headerName: "Camlar",
    type: "string[]",
    width: 120,
  },
  {
    field: "aynaKodu",
    headerName: "Ayna",
    width: 100,
  },
  {
    field: "musteriId",
    headerName: "Müşteri ID",
    width: 150,
  },
  {
    field: "siparisTarih",
    headerName: "Sipariş Tarihi",
    type: "date",
    width: 150,
  },
  { field: "siparisNot", headerName: "Sipariş Not", width: 150 },
  {
    field: "siparisFiyat",
    headerName: "Sipariş Fiyatı",
    align: "left",
    headerAlign: "left",
    type: "number",
    width: 150,
  },
];

const entityColumns = {
  Ayna: aynaCol,
  Cam: camCol,
  Cerceve: cerceveCol,
  Musteri: musteriCol,
  Paspartu: paspartuCol,
  Siparis: siparisCol,
};

const entityColumnsPost = {
  Ayna: aynaCol,
  Cam: camCol,
  Cerceve: cerceveCol,
  Musteri: musteriCol,
  Paspartu: paspartuCol,
  Siparis: siparisColPost,
};

export { entityColumns, entityColumnsPost, siparisCol};
