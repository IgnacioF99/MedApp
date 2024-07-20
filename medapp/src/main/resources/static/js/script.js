function searchTable() {
    var input = document.getElementById("searchDNI");
    var filter = input.value.toUpperCase();
    var table = document.getElementById("userTable");
    var tr = table.getElementsByTagName("tr");
  
    for (var i = 0; i < tr.length; i++) {
      var td = tr[i].getElementsByTagName("td")[0]; // Asume que el DNI está en la primera columna
      if (td) {
        var txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }