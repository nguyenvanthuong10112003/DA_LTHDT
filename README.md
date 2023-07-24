I.NHỮNG THỨ CẦN THIẾT
  - Một số chương trình, phần mềm, ... cần thiết để có thể mở và khởi
  chạy chương trình này:
      + WINRAR: đây là phần mềm lưu trữ tệp cho phép bạn nén và giải
      nén tệp.
      + Các phiên bản JDK và IDE hỗ trợ Java như Eclipse, Netbeans, ...
      nhưng ở đây sẽ chỉ hướng dẫn chạy trên IDE Eclipse.
      + Microsoft SQL Server: là phần mềm ứng dụng cho hệ thống quản
      trị cơ sở dữ liệu. Chương trình có thể không cần sử dụng tới MS
      SQL Server nhưng để có thể sử dụng cũng như khai thác hết tính
      năng của chương trình và tương tác với cơ sở dữ liệu thì chúng ta
      cần cài đặt và thiết lập một cách hợp lý. Tạo tài khoản, đơn giản
      nhất là thiết lập tài khoản sa, chúng ta có thể tham khảo cách làm
      trong đường link sau:
      [https://www.youtube.com/watch?v=ftVcBoZRAMA]

  - Đảm bảo các yêu cầu trên đã được cài đặt và thử nghiệm thành công:
      + Đối với IDE Eclipse, sau khi cài đặt thành công, phải chạy chương
      trình thử nghiệm thành công, chương trình thử nghiệm có thể là
      chương trình Java bất kỳ.
      + Nếu có MS SQL SERVER thì phải kết nối server thành công. Thực
      hiện thử nghiệm thành công kết nối Java với SQL Server trên
      Eclipse bằng JDBC - một API dùng để kết nối và thực thi các câu
      lệnh SQL xuống database. Chương trình không yêu cầu cài đặt
      JDBC vì thư viện này đã được cài đặt sẵn trong chương trình.
II.HƯỚNG DẪN CÀI ĐẶT

  - Sau khi tải về hoàn tất, chúng ta truy cập vào nơi mà chương trình được lưu vào. Sau đó giải nén file bằng WinRAR.

  - Thiết lập hệ cơ sở dữ liệu cho MS SQL SERVER, truy cập đường link sau để lấy tệp chứa câu lệnh tạo bảng và database đã tạo sẵn:
  [https://drive.google.com/drive/u/0/folders/1i4oUORfGnB1xzuaXS8VIap6vvCAjgbQf]
  - Chúng ta có thể import trực tiếp database từ tệp ADMIN.bacpac. Tham khảo cách làm ở video: [https://www.youtube.com/watch?v=34DQIR577m4]
  Ngoài ra, chúng ta có thể tạo trực tiếp database. Trước tiên, tạo database sau đó ta thực hiện câu lệnh tạo bảng từ tệp sql trên chính database vừa tạo.
  - Sau khi đã thiết lập hoàn tất, ta sử dụng IDE Eclipse để mở chương trình đã được giải nén. Chú ý phải mở đúng thư mục có tên là Program (DA_LTHDT/Program ) thì chương trình mới có thể chạy được.
  - Mở chương trình hoàn tất, ta truy cập vào tệp ConnectSQL.java ở đường link (Program/src/libary) để thiết lập một số thuộc tính:
    + Đổi thuộc tính serverName thành tên Server trên máy của bạn.
  	   VD: serverName = “MSI”;
    + Đổi thuộc tính userName thành tên đăng nhập của bạn.
  	   VD: userName = “sa”
    + Đổi thuộc tính password thành mật khẩu để đăng nhập tài khoản của bạn.
       VD: password = "a";
    + Đổi thuộc tính database thành tên database mà bạn vừa tạo, nếu bạn import trực tiếp và giữ nguyên tên thì không cần thay đổi thuộc tính này.
  	   VD: database = “ADMIN”;
  - Hoàn tất thiết lập chương trình, giờ chúng ta tới thư mục chứa hàm chính của chương trình để có thể khởi chạy và thử nghiệm: src/test/Main.java.
  - Nhấn chuột trái vào màn hình chính, sau đó sẽ hiển thị một danh sách các chức năng, chọn [Run As => Java Application]. 
  - Để biết chương trình đã kết nối được với cơ sở dữ liệu MS SQL SERVER hay chưa thì chúng ta có thể ấn nút Đăng nhập hoặc Đăng ký. Nếu sau khi ấn mà chương trình hiển thị lên một Form và không có gì xảy ra thì tức là chương trình đã kết nối thành công. Nếu không thì chương trình sẽ thông báo lỗi trên màn hình console hoặc bị đơ (chết) do không kết nối được với database. Để tắt chương trình, ta vào Task Manager chọn chương trình đang chạy, ấn chuột trái và ấn End task.
  - Có khá nhiều nguyên nhân dẫn đến việc không thể kết nối được database. Bạn thử kiểm tra lại các thuộc tính trong ConnectSQL.java xem đã đúng chưa và chạy lại. Nếu vẫn không được thì có thể tìm giải pháp, hỗ trợ kết nối khác và sửa đổi trong ConnectSQL.java.

III.HƯỚNG DẪN SỬ DỤNG

Chương trình thao tác với dữ liệu trên cả hai không gian là hệ thống lưu trữ và hệ thống cơ sở dữ liệu. Nên nếu vẫn chưa kết nối được database thì chúng ta cũng không cần phải lo lắng vì vẫn có thể sử dụng được chương trình.
Chương trình có giao diện thân thiện, dễ sử dụng và thao tác bao gồm các thành phần: thanh menu (ở trên cùng), thanh chức năng (dưới thanh menu), thanh địa chỉ và thanh tìm kiếm (dưới thanh chức năng), phần xem trước (ở bên trái), phần nội dung chính (phần ở giữa), và phần xem chi tiết (phần bên phải).
Mỗi thành phần có những đặc điểm, tác dụng riêng khác nhau góp phần tạo nên một chương trình hoàn chỉnh: 
Thanh menu: Gồm 3 mục File, Edit, View cho phép đóng mở thanh chức năng, sắp xếp folder/file theo tên hoặc thời gian sửa đổi gần nhất, hiển thị phần xem trước và phần xem chi tiết.
Thanh chức năng: là nơi để chỉnh sửa, thao tác với các folder/file như ghim vào truy cập nhanh, cắt, sao chép, dán, xóa, tạo mới,... ngoài ra còn là nơi để người dùng đăng nhập và đăng ký tài khoản, đăng xuất khi đăng nhập. Để có thể sử dụng được các chức năng này thì người dùng phải lưu ý các điều sau: 
Người dùng không thể sử dụng các chức năng này với
 	 folder đầu tiên (folder gốc) ngoại trừ chức năng ghim. 
Chức năng tạo file và folder mới có thể dùng ở bất cứ trường hợp nào trừ khi màn hình đang ở đầu. 
Cắt, copy, ghim, xóa chỉ có thể sử dụng khi người dùng ấn vào ít nhất 1 hàng dữ liệu. 
Đổi tên chỉ dùng được khi người dùng ấn vào 1 hàng dữ liệu. Nếu phần xem chi tiết đang đóng thì khi ấn vào nó sẽ hiện lên phần đó để có thể đổi tên. 
Chức năng dán chỉ có thể sử dụng khi người dùng cắt hoặc sao chép một số nội dung nào đó. Giả sử đang được phép dán. Sẽ có hai trường hợp khi dán: nếu không có hàng dữ liệu được chọn thì nó sẽ dán trực tiếp vào nơi hiện tại mà màn hình hiển thị, ngược lại nếu có một hàng dữ liệu được chọn và hàng đó là một folder thì sẽ được dán vào bên trong chính folder đang được chọn. Không thể dán một lúc vào nhiều folder trong một màn hình hiển thị nội dung nhưng có thể dán vào nhiều nơi trong chương trình miễn nơi chứa nó là một folder.
Đăng nhập và đăng ký: yêu cầu chương trình kết nối được database là một yếu tố tất yếu và quan trọng nhất để sử dụng chức năng này. Khi sử dụng các chức năng này, người dùng phải nhập đầy đủ và đúng các thông tin thì mới hợp lệ.
Khi người dùng đăng nhập, sẽ có thêm một nút đăng xuất dùng để đăng xuất khỏi tài khoản người dùng và trở về với dữ liệu ở hệ thống lưu trữ dữ liệu. 
Ngoài ra sẽ có một nút khi nhấn sẽ hiện lên một form có thể xem, sửa thông tin của người dùng. Nút này có dấu ba chấm đứng và sẽ ở cạnh nút đăng xuất. Khi ta muốn sửa thông tin thì ấn vào icon chiếc bút, màn hình sẽ hiện ra ô nhập nội dung muốn sửa thành sau đó ấn nút bên cạnh nút sửa để lưu. Tùy vào từng nội dung thuộc tính mà có cách sửa khác nhau. 
Hai nút tiến và lùi: nút lùi có tác dụng trở về trước của phần nội dung hiện tại hay nói cách khác nó sẽ trở về phần folder chứa chúng, nút này sẽ được phép dùng khi nội dung hiện tại không phải là gốc. Nút tiến có tác dụng đến phần mà bạn vừa trở về bằng nút lùi. Nút này được phép sử dụng khi dùng nút lùi nhưng sẽ không thể dùng được nếu nó đã đến nơi cuối mà chương trình đã truy cập đến. 
Có thể hiểu như sau: ví dụ ta có A < B < C < D, ban đầu sẽ là ở A cả 2 nút chưa được phép ấn. Nếu người dùng mở A thì sẽ đến B, lúc này nút tiến chưa được phép bấm, nút lùi được phép ấn, nếu ấn lùi thì nó sẽ lùi về A lúc này nút lùi không ấn được nữa vì nó đã ở gốc nhưng nút tiến thì đã có thể ấn, nếu ấn nút tiến lúc này thì nó sẽ đến B và lúc này nút lùi lại được phép ấn, nút tiến không được phép ấn nữa vì nó đã đến nơi cuối cùng mà chương trình đến.
Thanh địa chỉ: Hiển thị địa chỉ của các folder và file đang hiển thị trên phần nội dung, ngoài ra người dùng có thể nhập địa chỉ của 1 đối tượng và khi ấn enter chương trình sẽ tiến đến nơi đó nếu nó tồn tại. Đương nhiên, ô nhập này có gợi ý văn bản, nó sẽ xổ xuống một danh sách nếu nội dung bạn nhập tồn tại. Thanh địa chỉ này sẽ thay đổi nội dung khi người dùng vào, ra hoặc mở 1 folder.
Thanh tìm kiếm: Giúp tìm kiếm folder/file theo tên. Người dùng có thể nhập một tên nào đó, nếu tồn tại sẽ xổ xuống một danh sách các đối tượng đang tìm cùng vị trí của nó cách nhau bởi dấu  ‘ - ‘. Nếu ấn enter thì nó sẽ hiển thị danh sách những đối tượng phù hợp xuống phần nội dung. 
Phần xem trước: Hiển thị mục truy cập nhanh, hiển thị các thư mục ở dạng tổng quát (có thể nhìn được tất cả các thư mục trong chương trình). Phần này có thể thay đổi kích thước linh hoạt để phù hợp với góc nhìn người dùng. Ngoài ra còn cho phép mở folder trực tiếp bằng cách ấn chuột phải => Mở. Phần truy cập nhanh cho phép người dùng đến một folder nhanh chóng mà không phải mở thông qua nhiều folder cha. Tất nhiên có thể xóa một đối tượng khỏi truy cập nhanh.
Phần nội dung chính: Hiển thị icon, tên, thời gian sửa đổi gần nhất, tên định dạng và kích thước của từng đối tượng. Nếu đối tượng là folder thì thuộc tính kích thước sẽ không được hiển thị. Ngoài ra, có chức năng mở hay nói cách khác là đi vào trong một folder. Chúng ta có thể ấn 2 lần liên tiếp vào một hàng folder hoặc ấn chuột trái và ấn mở. 
Phần xem chi tiết: Hiển thị thông tin chi tiết của folder/file đang được chọn gồm tên, địa chỉ, định dạng, số lượng các tệp/thư mục con, thời gian khởi tạo, thời gian sửa đổi gần nhất, tổng kích thước. Ngoài ra còn cho phép đổi tên folder/file. Có thể đổi tên bất kỳ đối tượng nào thành tên mới với quy tắc các ký tự phải là ký tự từ a -> z và từ 1 -> 9, các ký tự đặc biệt trừ “/, \”. Đối với file, khi đổi tên phần sau của dấu “.” cuối cùng sẽ là định dạng của file. Phần này có thể điều chỉnh kích thước để người dùng có cái nhìn phù hợp và trực quan nhất có thể. 
