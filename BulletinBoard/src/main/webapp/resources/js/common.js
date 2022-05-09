$(document).ready(function() {

	new DateTime($('#date'), {
		format: 'YYYY-MM-DD'
	});

	new DateTime($('#date1'), {
		format: 'YYYY-MM-DD'
	});

	$('.delete').click(function() {
		$('.modalDelete').attr('href', $(this).attr('href'));
	});

	var utable = $('#uTable').DataTable({
		searching: false,
		paging: true,
		info: false,
		lengthChange: false,
		ordering: false,
		"columnDefs": [
			{
				"targets": [8],
				"visible": false,
				"searchable": true
			},
			{
				"targets": [9],
				"visible": false,
				"searchable": true
			}
		]
	});

	var ptable = $('#pTable').DataTable({
		searching: false,
		paging: true,
		info: false,
		lengthChange: false,
		ordering: false
	});

	$('#uTable tbody').on('click', 'tr', function() {
		var currentRow = $(this).closest("tr");
		var name = currentRow.find("td:eq(0)").text();
		$('.name').empty().append(name);
		$('.email').empty().append(utable.row(this).data()[1]);
		$('.createdUser').empty().append(utable.row(this).data()[2]);
		$('.phone').empty().append(utable.row(this).data()[3]);
		$('.dob').empty().append(utable.row(this).data()[4]);
		$('.address').empty().append(utable.row(this).data()[5]);
		$('.createdDate').empty().append(utable.row(this).data()[6]);
		$('.updatedDate').empty().append(utable.row(this).data()[7]);
		$('.type').empty().append(utable.row(this).data()[8]);
		$('.profile').empty().append(utable.row(this).data()[9]);
		$('.img').attr('src', utable.row(this).data()[9]);
	});

	$('#pTable tbody').on('click', 'tr', function() {
		var currentRow = $(this).closest("tr");
		var title = currentRow.find("td:eq(0)").text();
		$('.postTitle').empty().append(title);
		$('.postDesc').empty().append(ptable.row(this).data()[1]);
		$('.postedUser').empty().append(ptable.row(this).data()[2]);
		$('.postedDate').empty().append(ptable.row(this).data()[3]);
	});
});

$(function() {
	setTimeout(function() {
		$(".msg , .msg1, .msg2 ").hide();
	}, 2000);
});

function preview(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			document.querySelector("#img").setAttribute("src", e.target.result);
			$('#img').show();
			$('#img').addClass("img-fluid");
			$('.profile').val(e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	}
}