$(document).ready(function () {

    /* define static selectors */
    var searchSelector = $("#search");
    var userListSelector = $("#userList");
    var nextSelector = $(".next-li");
    //define global variables
    var pageCount = 0;
    var pageNumber = 1;
    var searchString = "";
    <!--TODO: zjistit hodnotu podkategorie -->

    //called when typing in search bar
    searchSelector.keyup(function () {

        //define dynamic selectors
        var pagerElemSelector = $(".pager-elem");

        /*define variables */
        var maxResults;
        var subcatId;
        //reset pageNumber if searchString has changed
        if (searchString != searchSelector.val()) {
            pageNumber = 1;
        }

        //fill variables
        maxResults = $(this).data("max-results");
        subcatId = $(this).data("subcategory-id");
        searchString = searchSelector.val();
        $.ajax({
            type: "GET",
            //url: "http://isarg.feld.cvut.cz:2001/GENEPI/user/list-search",
            url: "http://premium-spirit-society.com/product/listInSubcat-search",
            data: "search=" + searchString + "&maxResults=" + maxResults + "&pageNumber=" + pageNumber+"&subcategoryId="+subcatId,
            success: function (response) {
                var obj = JSON.parse(response);
                var countOfusers = obj.userList.length;

                //delete content of users list
                userListSelector.html("");

                //fill content of users list
                for (var i = 0; i < countOfusers; i++) {

                    var productId = obj.userList[i][0].productId;
                    var productName = obj.userList[i][0].productName;
                    var productCatUrl = obj.userList[i][0].productCatUrl;
                    var productSubcatUrl = obj.userList[i][0].productSubcatUrl;
                    var productPrice = obj.userList[i][0].productPrice;
                    var productProducer = obj.userList[i][0].productProducer;
                    var productUrl = obj.userList[i][0].productUrl;

                    userListSelector.html(userListSelector.html() + "<tr class='clickable-row' data-href='/" + productCatUrl + "/" + productSubcatUrl + "/" + productUrl + "'><td>"
                    + productPrice + "</td>" + "<td>"
                    + productName + "</td>" +
                    "<td>" + productProducer + "</td> </tr>");
                }

                //make rows in users list clickable
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });

                //count how many pages are need for users matching searchString
                pageCount = Math.ceil(obj.usersCount / maxResults);

                //remove all numbers from pager
                pagerElemSelector.remove();

                //generate numbers into pager
                for (var i = 1; i <= pageCount; i++) {
                    if (pageNumber == i) {
                        nextSelector.before('<li class="pager-elem active"><a class="page-number" href="#" data-page-number="' + i + '">' + i + '</a></li>');
                    } else {
                        nextSelector.before('<li class="pager-elem"><a class="page-number" href="#" data-page-number="' + i + '">' + i + '</a></li>');
                    }
                }

                //make numbers in pager clickable
                $(".page-number").click(function () {
                    pageNumber = $(this).data("page-number");
                    searchSelector.keyup();
                });
            },
            error: function (e) {
                alert("Error occured" +e);
            }
        });
    });

    $(".start").click(function () {
        pageNumber = 1;
        searchSelector.keyup();
    });


    $(".end").click(function () {
        pageNumber = pageCount;
        searchSelector.keyup();
    });


    $(".next").click(function () {
        if (pageCount != pageNumber) {
            pageNumber++;
            searchSelector.keyup();
        }
    });


    $(".prev").click(function () {
        if (pageNumber != 1) {
            pageNumber--;
            searchSelector.keyup();
        }
    });

    /* trigger events */
    searchSelector.keyup();
});