
let searchValue = $("#search_value");
let filterValue = $("#filter_value");
let index_page = $(".index_page");
let submit_search = $(".submit_search");
let previous = $(".previous");
let next_page = $(".next_page");
let viewListEmp = $("#view_list_emp");

let manager = $(".manager")
let contentChoice = $(".choice")

manager.on("click", function() {
    contentChoice.toggle();
});

let reduceIndex = function() {
    let numberValue = parseInt(index_page.text());
    if(numberValue > 1 ){
        index_page.text(numberValue - 1);
    } else {
        index_page.text(1);
    }
    viewList();
}


let increaseIndex = function() {
    let numberValue = parseInt(index_page.text());
    index_page.text(numberValue + 1)
    viewList();

}

var viewList = function () {
    let value = {
        "searchValue" : searchValue.val(),
        "filterValue" : filterValue.val(),
        "numberValue" : index_page.text()
    }
    $.ajax({
        url: "http://localhost:8080/home/employees", // Đường dẫn tới tài nguyên hoặc API bạn muốn truy cập
        type: "POST", // Ví dụ: "GET", "POST", "PUT", "DELETE"
        data: JSON.stringify(value), // Dữ liệu gửi kèm với yêu cầu (nếu cần)
        headers: { 'Content-Type': 'application/json', 'mode': 'no-cors' },
        dataType: "json",
        success: function (data) {
            if ($.isEmptyObject(data)) {
                index_page.text(parseInt(index_page.text()) - 1)
                viewlist();
            }
            let html;
            data.forEach(e => {
                html += `
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.firstName + " " + e.lastName}</td>
                        <td>${e.dob}</td>
                        <td>${e.address}</td>
                        <td>${e.phone}</td>
                        <td>${e.departmentName}</td>
                        <td>
                            <a href="http://localhost:8080/home/detailEmployee/${e.id}">
                            <i class="fa-solid fa-eye"></i>
                            <span>View</span>
                            </a>
                        </td>
                    </tr>
                `
            })
            viewListEmp.html(html);

        },
        error: function (e) {
            console.log("error")
            console.log(e);
        }
    })
}

var viewListSearch = function () {
    let value = {
        "searchValue" : searchValue.val(),
        "filterValue" : filterValue.val(),
        "numberValue" : 1
    }
    index_page.text(1);
    $.ajax({
        url: "http://localhost:8080/home/employees", // Đường dẫn tới tài nguyên hoặc API bạn muốn truy cập
        type: "POST", // Ví dụ: "GET", "POST", "PUT", "DELETE"
        data: JSON.stringify(value), // Dữ liệu gửi kèm với yêu cầu (nếu cần)
        headers: { 'Content-Type': 'application/json', 'mode': 'no-cors' },
        dataType: "json",
        success: function (data) {
            let html;
            data.forEach(e => {
                html += `
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.firstName + " " + e.lastName}</td>
                        <td>${e.dob}</td>
                        <td>${e.address}</td>
                        <td>${e.phone}</td>
                        <td>${e.departmentName}</td>
                        <td>
                            <a href="http://localhost:8080/home/detailEmployee/${e.id}">
                            <i class="fa-solid fa-eye"></i>
                            <span>View</span>
                            </a>
                        </td>
                    </tr>
                `
            })
            viewListEmp.html(html);
        },
        error: function (e) {
            console.log("error")
            console.log(e);
        }
    })
}

previous.on("click", reduceIndex);
next_page.on("click", increaseIndex);
submit_search.on("click", viewListSearch);