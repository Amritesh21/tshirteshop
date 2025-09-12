//function fileToBase64(file) {
//
//    return new Promise((resolve, reject) => {
//        const reader = new FileReader();
//        reader.onload = () => resolve(reader.result.split(",")[1]);
//        reader.onError = (error) => reject(error);
//
//        reader.readAsDataURL(file);
//    });
//
//}
//
//
////document.getElementById("fileUploader").addEventListener("change", async (event) => {
////    const file = event.target.files[0];
////    const base64String = await fileToBase64(file);
////    console.log(event.target.files[0]);
////});
//
//function saveImage(formData) {
//    return fetch("http://localhost:8080/api/inventory/product/image/add", {
//        method: "POST",
//        body: JSON.stringify(formData),
//        headers: {
//            "Content-Type": "application/json"
//        }
//    })
//}
//
//class ProductImage{
//
//    productId;
//    imageName;
//    imageFile;
//
//    set productId(productId) {
//        this.productId = productId;
//    }
//
//    get productId() {
//        return this.productId;
//    }
//
//    set imageName(imageName) {
//        this.imageName = imageName;
//    }
//
//    get imageName() {
//        return this.imageName;
//    }
//
//    set imageFile(imageFile) {
//        this.imageFile = imageFile;
//    }
//
//    get imageFile() {
//        return this.imageFile;
//    }
//
//}
//
//document.getElementById("imageUpload").addEventListener("click", async (event) => {
//    const file = document.getElementById("fileUploader").files[0];
//    const base64String = await fileToBase64(file);
//    console.log(base64String);
//    const formData = new ProductImage();
//    formData.productId = "1";
//    formData.imageName = file.name;
//    formData.imageFile = base64String;
//    const response = await saveImage(formData);
//    console.log(response.status);
//})


function addImage(formData) {
    return fetch("http://localhost:8081/api/inventory/product/image/add", {
        method: "POST",
        body: formData
    });
}

document.getElementById("imageUpload").addEventListener("click", async (event) => {

    const file = document.getElementById("fileUploader").files[0];
    const formData = new FormData();
    formData.append("productId", "tshirt1_2025-09-09T19:08:54.113582");
    formData.append("imageName", file.name);
    formData.append("imageFile", file);
    const response = await addImage(formData);
    console.log(response.status);
});