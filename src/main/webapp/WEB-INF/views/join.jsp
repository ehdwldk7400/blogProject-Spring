<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="resources/css/Default.css">
<link rel="stylesheet" href="resources/css/join.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;700;900&display=swap"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="wrap-contents">
			<div>
				<div class="contents">
					<div class="content-section">
						<div class="content-header">
							<h1 class="title">정보입력</h1>
							<p class="desc">계정 정보를 입력해 주세요.</p>
						</div>
						<form action="#" method="post" autocomplete="off">
							<article class="simgup-form">
								<div class="input-group field-button">
									<div class="input-field">
										<div class="input-wrap">
											<input type="text" id="userid" placeholder="계정 (이메일)"
												maxlength="64">
										</div>
										<div class="btn-wrap">
											<button type="button" disabled="disabled" class="btn-primary">
												<span>인증메일 발송</span>
											</button>
										</div>
										<div class="btn-reset" style="display: none;">
											<button type="button">
												<span>취소</span>
											</button>
										</div>
										<div class="icon-confirm" style="display: none;">
											<span>확인</span>
										</div>
									</div>
									<!-- input-filed -->
								</div>
								<!-- input-group field-button -->
								<div class="number" style="display: none;">
									<div class=" input-group field-button">
										<div class="input-field">
											<div class="input-wrap">
												<input type="text" id="userid" placeholder="인증번호"
													maxlength="6">
											</div>
											<div class="timer" style="display: none;">
												<p>시간 만료</p>
											</div>
											<div class="btn-wrap">
												<button type="button" class="btn-primary"
													disabled="disabled">
													<span>인증메일 확인</span>
												</button>
											</div>
										</div>
										<div class="content-info">
											인증번호를 받지 못하셨나요? <a href="#" class="btn-link">인증메일 재발송</a>
										</div>
									</div>
								</div>
								<!-- number -->

								<div class="input-group">
									<div class="input-field password">
										<div class="input-wrap">
											<input disabled="disabled" type="password" id="userpw"
												name="userpw" placeholder="비밀번호" maxlength="16">
										</div>
										<div class="pwd-strength-meter" data-meter="1"
											style="display: none;">
											<p class="grade">위험</p>
										</div>
									</div>
									<div class="msg">8 ~ 16자 영문 대소문자,숫자,특수문자를 조합해 주세요.</div>
								</div>
								<!-- input-group END -->
								<div class="input-group">
									<div class="input-field">
										<div class="input-wrap">
											<input disabled="disabled" type="password"
												placeholder="비밀번호 확인" maxlength="16" id="pw-confirm">
										</div>
										<div class="icon-confirm" style="display: none;">
											<span>확인</span>
										</div>
									</div>
									<div class="msg">비밀번호를 한번 더 입력해 주세요.</div>
								</div>
								<!-- input-group END -->
								<div class="input-group">
									<div class="input-field">
										<div class="input-wrap">
											<input disabled="disabled" type="text" id="username"
												name="username" placeholder="이름">
										</div>
									</div>
								</div>

								<div class="input-radio-group">

									<label for="men" class="ck-radio"> <input type="radio"
										id="men" name="gender" value="남자" checked disabled="disabled">
										<span>남자</span>
									</label> <label for="girl" class="ck-radio"> <input
										type="radio" id="girl" name="gender" value="여자"
										disabled="disabled"> <span>여자</span>
									</label>
								</div>


								<div class="btn-warp">
									<button type="submit" class="btn-primary" disabled="disabled">
										<span>회원가입</span>
									</button>
								</div>
							</article>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>