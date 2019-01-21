from django.urls import path
from . import views
urlpatterns = [
    path('', views.home,name='api-home'),
    path('validate/',views.validate,name='api-validate')
]
