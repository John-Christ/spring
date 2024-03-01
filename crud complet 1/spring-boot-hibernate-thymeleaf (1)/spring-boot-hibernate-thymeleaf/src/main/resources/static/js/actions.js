
  $('td').click(function() {

   $.ajax({
          url: "http://rest-service.guides.spring.io/greeting"
      }).then(function() {
 const row = $(this).parent().parent().children().index($(this).parent());
const nRow  =  Number(row) + 1;

const firstname= document.getElementById('elBor').rows[nRow].cells[0].innerHTML;
const lastname= document.getElementById('elBor').rows[nRow].cells[1].innerHTML;
const email=document.getElementById('elBor').rows[nRow].cells[2].innerHTML;
const phone= document.getElementById('elBor').rows[nRow].cells[3].innerHTML;


document.getElementById("firstName").value = firstname;
document.getElementById('lastName').value=lastname;
document.getElementById('email').value= email;
document.getElementById('phone').value= phone;

});

});




/*
function test () {
alert("JS Works");
}



// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}




 $(document).on('click', '#out', function () {//Button inside a cell
     var current_row = $(this).parents('td');//Get the current row
     if (current_row.hasClass('child')) {//Check if the current row is a child row
         current_row = current_row.prev();//If it is, then point to the row before it (its 'parent')
     }
     var data = products.row(current_row).data();//At this point, current_row refers to a valid row in the table, whether is a child row (collapsed by the DataTable's responsiveness) or a 'normal' row
     console.log('Row data:'+data);
      var firstname =  current_row.cells[0].innerHTML;
 document.getElementById("firstName").value = firstname;
 });


   function display() {
var table = document.getElementById('elBor').rows[1];
var row = document.getElementById("elBor").rows.click();

var first = document.getElementById('elBor').rows[1].cell[0].innerHTML;

var last = document.getElementById('elBor').rows[1].cell[1].innerHTML;

var mail=document.getElementById('elBor').rows[1].cell[2].innerHTML;

var hone= document.getElementById('elBor').rows[1].cell[3].innerHTML;

for (let i = 0, i > 1000, i++) {

var firstname= document.getElementById('elBor').rows[i].cell[0].innerHTML;

var lastname= document.getElementById('elBor').rows[i].cell[1].innerHTML;

var email=document.getElementById('elBor').rows[i].cell[2].innerHTML;

var phone= document.getElementById('elBor').rows[i].cell[3].innerHTML;

if (row !== table) {
document.getElementById('firsName').value= firstname;
document.getElementById('lasName').value=lastname;
document.getElementById('email').value= email;
document.getElementById('phone').value= phone;

}
else if (row == table) {
document.getElementById('firsName').value= first;
document.getElementById('lasName').value=last;
document.getElementById('email').value= mail;
document.getElementById('phone').value= hone;
}
}
}





  /*
  function display() {

var firstname =  document.getElementById("elBor").rows[*].cells[0].innerHTML;
document.getElementById("f1").value = firstname;

  var table = document.getElementById("elBor");
for (var i = 0, row; row = table.rows[i]; i++) {
   var firstname = table.rows[i].cells[0].innerHTML;
   document.getElementById("f1").value = firstname;

   var lastname = table.rows[i].cells[1].innerHTML;
   document.getElementById("f2").value = lastname;

    var email = table.rows[i].cells[2].innerHTML;
   document.getElementById("f3").value = email;

    var phone = table.rows[i].cells[3].innerHTML;
   document.getElementById("f4").value = phone;



}




    function go () {

var firstname = document.getElementById("firstName").value;
var lastname = document.getElementById("lastName").value;
var email = document.getElementById("email").value;
var phone = document.getElementById("phone").value;

if ( firstname == "" ) {

document.getElementById("chiba").style.display = "block";
document.getElementById("submit").style.display = "none";
document.getElementById("f1").style.display = "block";


}

else if ( lastname == "" ) {

document.getElementById("chiba").style.display = "block";
document.getElementById("submit").style.display = "none";
document.getElementById("f2").style.display = "block";
document.getElementById("f1").style.display = "none";


}

else if ( email == "" ) {

document.getElementById("chiba").style.display = "block";
document.getElementById("submit").style.display = "none";
document.getElementById("f3").style.display = "block";
document.getElementById("f2").style.display = "none";


}

else if ( phone == "" ) {

document.getElementById("chiba").style.display = "block";
document.getElementById("submit").style.display = "none";
document.getElementById("f4").style.display = "block";
document.getElementById("f3").style.display = "none";


}


else if ( firstname !== "" || lastname !== "" || email !== "" || phone !== "" )  {

document.getElementById("chiba").style.display = "none";
document.getElementById("submit").style.display = "block";
document.getElementById("p1").style.display = "block";
document.getElementById("f1").style.display = "none";
document.getElementById("f2").style.display = "none";
document.getElementById("f3").style.display = "none";
document.getElementById("f4").style.display = "none";
}

}

*/



