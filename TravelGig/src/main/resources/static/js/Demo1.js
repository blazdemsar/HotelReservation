$(document).ready(function() {
		/* $("#country").change(function() {
			var selectedCountry=$(this).val();
			$("#txtBox").val(selectedCountry);
		}); */
		/* $(".cssCountry").change(function() {
			var selectedCountry=$(this).val();
			$("#txtBox").val(selectedCountry);
		}); */

		$("select").change(function() {
			var selectedCountry = $(this).val();
			$("#txtBox").val(selectedCountry);
		});
		
		$("#countryTable").on("change",".tblSelect",function() {
			var selectedCountry = $(this).val();
			$("#txtBox").val(selectedCountry);
		});
		
		$("#countryTable").on("click",".rowAdd",function() {
			$("#countryTable").append("<tr><td>India</td><td>New Delhi</td><td><select class='tblSelect'><option>Select</option><option>India</option><option>US</option><option>Germany</option></select></td><td><a href='#' class='rowAdd'>ADD</a></td></tr>");
		});
		
		$("#show").click(function(){
			
			$("#hide").show();
			
		});
		
		$("#hide").click(function(){
			
			$("#hide").hide();
			
		});
		
		$("#appCss").click(function(){
			
			$("h1").css({"background-color":"blue"});
			
		});
		
		$("#fadeOut").click(function(){
			
			$("h1").fadeOut();
			
		});
		
		$("#fadeIn").click(function(){
			
			$("h1").fadeIn();
			
		});
		//ul>a only the immediate children
		//ul a children in whole ul tree
		/*$("ul>a").click(function(){
			
			$(this).hide();
			
		});*/
		
		$("a[href='www']").click(function(){
			
			$(this).hide();
			
		});
	});