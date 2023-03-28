

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="w-100 d-flex justify-content-center p-5">
           <div class="card" style="width: 38rem;">
            <div class="card-body">
                <form action="uploadCover" method="POST" enctype="multipart/form-data">
                    <div class="mb-3 row">
                        <label for="fileName" class="col-sm-5 col-form-label">Обложка товара:</label>
                        <div class="col-sm-7">
                          <input type="file" class="form-control" id="fileName" name="file" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="descript" class="col-sm-5 col-form-label">Описание файла</label>
                        <div class="col-sm-7">
                          <input type="text" class="form-control" id="descript" name="description" value="">
                        </div>
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-primary me-md-2">Загрузить</button>
                    </div>
                </form>
            </div>
           </div>
        </div>
