// main.tf defines what resources to create

terraform {
  required_version = ">= 1.5.0"
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 5.0"
    }
  }
}

provider "google" {
  project = var.project_id
  region  = var.region
}

# Enable APIs
resource "google_project_service" "run" {
  service = "run.googleapis.com"
}

resource "google_project_service" "artifactregistry" {
  service = "artifactregistry.googleapis.com"
}

# Service account for Cloud Run
resource "google_service_account" "cloudrun" {
  account_id   = "cloudrun-sa"
  display_name = "Cloud Run Service Account"
}

# Artifact Registry repo
resource "google_artifact_registry_repository" "repo" {
  location      = var.region
  repository_id = "drive-time-repo"
  format        = "DOCKER"
}

# Cloud Run service
resource "google_cloud_run_v2_service" "service" {
  name     = "drive-time-api"
  location = var.region

  template {
    service_account = google_service_account.cloudrun.email

    containers {
      image = var.container_image
      env {
        name  = "GOOGLE_ROUTES_API_KEY"
        value = var.google_routes_api_key
      }
    }
  }
}

# Allow public access
resource "google_cloud_run_v2_service_iam_member" "invoker" {
  location = var.region
  name     = google_cloud_run_v2_service.service.name
  role     = "roles/run.invoker"
  member   = "allUsers"
}
